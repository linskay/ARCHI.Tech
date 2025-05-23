import React from 'react';
import { Box, Typography, Paper, Grid, Button } from '@mui/material';
import { motion } from 'framer-motion';
import { useNavigate } from 'react-router-dom';

const HomePage: React.FC = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');
    localStorage.removeItem('user');
    navigate('/login');
  };

  // Если пользователь не аутентифицирован, перенаправляем на страницу входа
  if (!isAuthenticated) {
    return null; 
  }

  return (
    <Box sx={{ py: 4 }}>
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <Paper
          elevation={3}
          sx={{
            p: 4,
            borderRadius: 2,
            background: 'rgba(255, 255, 255, 0.95)',
            backdropFilter: 'blur(10px)',
          }}
        >
          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 4 }}>
            <Typography variant="h4" component="h1" sx={{ fontWeight: 700 }}>
              Добро пожаловать, {user.email || 'Гость'}!
            </Typography>
            <Button
              variant="contained"
              onClick={handleLogout}
              sx={{
                background: 'linear-gradient(45deg, #ff4b2b, #ff416c)',
                '&:hover': {
                  background: 'linear-gradient(45deg, #ff416c, #ff4b2b)',
                },
              }}
            >
              Выйти
            </Button>
          </Box>
          
          <Box 
            sx={{
              width: '100%',
              height: 400,
              bgcolor: 'lightgrey',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
              mb: 4,
              borderRadius: 2,
              color: 'text.secondary',
            }}
          >
            Placeholder для карты с расположением коворкингов
          </Box>

          <Typography variant="body1" paragraph sx={{ mb: 4 }}>
            Мы рады видеть вас в нашем коворкинге. Здесь вы можете найти идеальное рабочее пространство,
            забронировать место и управлять своими бронированиями.
          </Typography>

          <Box
            sx={{
              width: '100%',
              minHeight: 200,
              bgcolor: '#e0e0e0',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
              mt: 4,
              borderRadius: 2,
              color: 'text.secondary',
            }}
          >
            Placeholder для мини презентации коворкингов
          </Box>

        </Paper>
      </motion.div>
    </Box>
  );
};

export default HomePage; 