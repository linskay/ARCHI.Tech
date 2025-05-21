import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink, useNavigate } from 'react-router-dom';

const navButtonStyle = {
  transition: 'all 0.3s cubic-bezier(.4,2,.6,1)',
  background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
  color: '#fff',
  marginLeft: 1,
  '&:hover': {
    transform: 'scale(1.12)',
    background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
    boxShadow: '0 4px 20px 0 rgba(33,147,176,0.3)',
  },
};

const Navigation: React.FC = () => {
  const navigate = useNavigate();
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');
    navigate('/login');
  };

  if (!isAuthenticated) {
    return null;
  }

  return (
    <AppBar position="static" sx={{ background: 'rgba(30,60,114,0.95)', boxShadow: '0 2px 16px 0 rgba(33,147,176,0.2)' }}>
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Коворкинг
        </Typography>
        <Box>
          <Button sx={navButtonStyle} component={RouterLink} to="/">
            Главная
          </Button>
          <Button sx={navButtonStyle} component={RouterLink} to="/workspaces">
            Рабочие пространства
          </Button>
          <Button sx={navButtonStyle} component={RouterLink} to="/bookings">
            Бронирования
          </Button>
          <Button sx={navButtonStyle} component={RouterLink} to="/profile">
            Профиль
          </Button>
          <Button sx={navButtonStyle} onClick={handleLogout}>
            Выйти
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navigation; 