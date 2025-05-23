import React, { useState } from 'react';
import {
  Box,
  Container,
  TextField,
  Button,
  Typography,
  Paper,
  useTheme,
  useMediaQuery,
  Alert,
} from '@mui/material';
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
import { Email as EmailIcon } from '@mui/icons-material';

const ForgotPasswordPage: React.FC = () => {
  const [email, setEmail] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Implement password reset logic
    setIsSubmitted(true);
  };

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
                Forgot Password
              </Typography>
            </motion.div>

            {isSubmitted ? (
              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5 }}
              >
                <Alert severity="success" sx={{ mb: 3 }}>
                  Password reset instructions have been sent to your email.
                </Alert>
                <Typography variant="body1" align="center" sx={{ mb: 3 }}>
                  Please check your email for instructions to reset your password.
                  If you don't see the email, please check your spam folder.
                </Typography>
                <Button
                  fullWidth
                  variant="contained"
                  component={Link}
                  to="/login"
                  sx={{
                    borderRadius: 2,
                    py: 1.5,
                    background: 'linear-gradient(45deg, #1a237e, #0d47a1)',
                    '&:hover': {
                      background: 'linear-gradient(45deg, #0d47a1, #1a237e)',
                    },
                  }}
                >
                  Return to Login
                </Button>
              </motion.div>
            ) : (
              <form onSubmit={handleSubmit}>
                <motion.div
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ duration: 0.5, delay: 0.2 }}
                >
                  <Typography variant="body1" align="center" sx={{ mb: 3 }}>
                    Enter your email address and we'll send you instructions to
                    reset your password.
                  </Typography>

                  <TextField
                    fullWidth
                    label="Email"
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    sx={{ mb: 3 }}
                    InputProps={{
                      sx: { borderRadius: 2 },
                      startAdornment: (
                        <EmailIcon
                          sx={{ color: 'text.secondary', mr: 1 }}
                        />
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
                    Send Reset Instructions
                  </Button>

                  <Box sx={{ textAlign: 'center' }}>
                    <Typography variant="body2" color="text.secondary">
                      Remember your password?{' '}
                      <Link
                        to="/login"
                        style={{
                          color: theme.palette.primary.main,
                          textDecoration: 'none',
                          fontWeight: 600,
                        }}
                      >
                        Sign In
                      </Link>
                    </Typography>
                  </Box>
                </motion.div>
              </form>
            )}
          </Paper>
        </motion.div>
      </Container>
    </Box>
  );
};

export default ForgotPasswordPage; 