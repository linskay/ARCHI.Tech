import React, { useState, useEffect } from 'react';
import {
  Box,
  Container,
  Button,
  Typography,
  Paper,
  useTheme,
  useMediaQuery,
  CircularProgress,
  TextField,
} from '@mui/material';
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
import { Email as EmailIcon, Timer as TimerIcon } from '@mui/icons-material';

const EmailVerificationPage: React.FC = () => {
  const [verificationCode, setVerificationCode] = useState('');
  const [timeLeft, setTimeLeft] = useState(300); // 5 minutes in seconds
  const [isResending, setIsResending] = useState(false);
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));

  useEffect(() => {
    const timer = setInterval(() => {
      setTimeLeft((prev) => (prev > 0 ? prev - 1 : 0));
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Implement verification logic
  };

  const handleResendCode = () => {
    setIsResending(true);
    // TODO: Implement resend logic
    setTimeout(() => {
      setIsResending(false);
      setTimeLeft(300);
    }, 2000);
  };

  const formatTime = (seconds: number) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
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
              <Box
                sx={{
                  display: 'flex',
                  justifyContent: 'center',
                  mb: 3,
                }}
              >
                <motion.div
                  animate={{
                    scale: [1, 1.2, 1],
                  }}
                  transition={{
                    duration: 2,
                    repeat: Infinity,
                    repeatType: 'reverse',
                  }}
                >
                  <EmailIcon
                    sx={{
                      fontSize: 64,
                      color: theme.palette.primary.main,
                    }}
                  />
                </motion.div>
              </Box>

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
                  mb: 2,
                }}
              >
                Verify Your Email
              </Typography>

              <Typography
                variant="body1"
                align="center"
                color="text.secondary"
                sx={{ mb: 4 }}
              >
                We've sent a verification code to your email address. Please enter
                the code below to verify your account.
              </Typography>

              <form onSubmit={handleSubmit}>
                <motion.div
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ duration: 0.5, delay: 0.2 }}
                >
                  <TextField
                    fullWidth
                    label="Verification Code"
                    value={verificationCode}
                    onChange={(e) => setVerificationCode(e.target.value)}
                    sx={{ mb: 3 }}
                    InputProps={{
                      sx: { borderRadius: 2 },
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
                    Verify Email
                  </Button>

                  <Box
                    sx={{
                      display: 'flex',
                      alignItems: 'center',
                      justifyContent: 'center',
                      gap: 1,
                      mb: 2,
                    }}
                  >
                    <TimerIcon color="action" />
                    <Typography variant="body2" color="text.secondary">
                      Code expires in: {formatTime(timeLeft)}
                    </Typography>
                  </Box>

                  <Box sx={{ textAlign: 'center' }}>
                    <Button
                      onClick={handleResendCode}
                      disabled={timeLeft > 0 || isResending}
                      sx={{
                        color: theme.palette.primary.main,
                        '&:disabled': {
                          color: theme.palette.text.disabled,
                        },
                      }}
                    >
                      {isResending ? (
                        <CircularProgress size={20} />
                      ) : (
                        'Resend Verification Code'
                      )}
                    </Button>
                  </Box>
                </motion.div>
              </form>
            </motion.div>
          </Paper>
        </motion.div>
      </Container>
    </Box>
  );
};

export default EmailVerificationPage; 