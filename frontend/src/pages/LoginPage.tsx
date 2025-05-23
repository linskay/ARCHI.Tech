import React, { useState } from 'react';
import {
  Box,
  Container,
  TextField,
  Button,
  Typography,
  Paper,
  IconButton,
  InputAdornment,
  Divider,
  useTheme,
  useMediaQuery,
  Alert,
} from '@mui/material';
import {
  Visibility,
  VisibilityOff,
  Google as GoogleIcon,
  Facebook as FacebookIcon,
  GitHub as GitHubIcon,
} from '@mui/icons-material';
import { motion } from 'framer-motion';
import { Link, useNavigate } from 'react-router-dom';

const LoginPage: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      console.log('Login attempt with:', { email, password });
      
      // TODO: Заменить на реальный API-запрос
      // Временная имитация успешной аутентификации
      if (email && password) {
        console.log('Setting authentication data');
        localStorage.setItem('isAuthenticated', 'true');
        localStorage.setItem('user', JSON.stringify({ email }));
        localStorage.setItem('hasCompletedOnboarding', 'true');
        console.log('Authentication data set, navigating to home');
        navigate('/');
      } else {
        console.log('Empty fields');
        setError('Пожалуйста, заполните все поля');
      }
    } catch (err) {
      console.error('Login error:', err);
      setError('Ошибка при входе. Пожалуйста, попробуйте снова.');
    }
  };

  const socialButtons = [
    { icon: <GoogleIcon />, color: '#DB4437', label: 'Google' },
    { icon: <FacebookIcon />, color: '#4267B2', label: 'Facebook' },
    { icon: <GitHubIcon />, color: '#333', label: 'GitHub' },
  ];

  return (
    <Box
      sx={{
        minHeight: '100vh',
        display: 'flex',
        alignItems: 'center',
        background: 'linear-gradient(135deg, #1a237e 0%, #0d47a1 100%)',
        py: 4,
      }}
    >
      <Container maxWidth="sm">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          <Paper
            elevation={24}
            sx={{
              p: 4,
              borderRadius: 2,
              background: 'rgba(255, 255, 255, 0.95)',
              backdropFilter: 'blur(10px)',
            }}
          >
            <motion.div
              initial={{ scale: 0.9 }}
              animate={{ scale: 1 }}
              transition={{ duration: 0.3 }}
            >
              <Typography
                variant="h4"
                component="h1"
                align="center"
                gutterBottom
                sx={{
                  fontWeight: 700,
                  background: 'linear-gradient(45deg, #1a237e, #0d47a1)',
                  backgroundClip: 'text',
                  textFillColor: 'transparent',
                  mb: 4,
                }}
              >
                Welcome Back
              </Typography>
            </motion.div>

            {error && (
              <Alert severity="error" sx={{ mb: 2 }}>
                {error}
              </Alert>
            )}

            <form onSubmit={handleSubmit}>
              <TextField
                fullWidth
                label="Email"
                variant="outlined"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                sx={{ mb: 2 }}
                InputProps={{
                  sx: { borderRadius: 2 },
                }}
              />
              <TextField
                fullWidth
                label="Password"
                type={showPassword ? 'text' : 'password'}
                variant="outlined"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                sx={{ mb: 3 }}
                InputProps={{
                  sx: { borderRadius: 2 },
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        onClick={() => setShowPassword(!showPassword)}
                        edge="end"
                      >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                      </IconButton>
                    </InputAdornment>
                  ),
                }}
              />

              <Button
                fullWidth
                variant="contained"
                size="large"
                type="submit"
                sx={{
                  borderRadius: 2,
                  py: 1.5,
                  mb: 3,
                  background: 'linear-gradient(45deg, #1a237e, #0d47a1)',
                  '&:hover': {
                    background: 'linear-gradient(45deg, #0d47a1, #1a237e)',
                  },
                }}
              >
                Sign In
              </Button>

              <Box sx={{ textAlign: 'center', mb: 3 }}>
                <Link
                  to="/forgot-password"
                  style={{
                    color: theme.palette.primary.main,
                    textDecoration: 'none',
                  }}
                >
                  Forgot Password?
                </Link>
              </Box>

              <Divider sx={{ my: 3 }}>
                <Typography variant="body2" color="text.secondary">
                  OR CONTINUE WITH
                </Typography>
              </Divider>

              <Box
                sx={{
                  display: 'flex',
                  justifyContent: 'center',
                  gap: 2,
                  flexWrap: 'wrap',
                }}
              >
                {socialButtons.map((button, index) => (
                  <motion.div
                    key={button.label}
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: index * 0.1 }}
                  >
                    <IconButton
                      sx={{
                        width: 50,
                        height: 50,
                        backgroundColor: button.color,
                        color: 'white',
                        '&:hover': {
                          backgroundColor: button.color,
                          opacity: 0.9,
                        },
                      }}
                    >
                      {button.icon}
                    </IconButton>
                  </motion.div>
                ))}
              </Box>

              <Box sx={{ textAlign: 'center', mt: 3 }}>
                <Typography variant="body2" color="text.secondary">
                  Don't have an account?{' '}
                  <Link
                    to="/register"
                    style={{
                      color: theme.palette.primary.main,
                      textDecoration: 'none',
                      fontWeight: 600,
                    }}
                  >
                    Sign Up
                  </Link>
                </Typography>
              </Box>
            </form>
          </Paper>
        </motion.div>
      </Container>
    </Box>
  );
};

export default LoginPage; 