import React from 'react';
import { Box, Typography, Paper, List, ListItem, ListItemText, Button } from '@mui/material';

const FoodCartPage: React.FC = () => {
  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 6,
        borderRadius: 6,
        background: 'linear-gradient(135deg, #232526 0%, #414345 100%)',
        boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
        animation: 'fadeIn 1.2s cubic-bezier(.4,2,.6,1)',
        maxWidth: 480,
        width: '100%',
        '@keyframes fadeIn': {
          from: { opacity: 0, transform: 'scale(0.95)' },
          to: { opacity: 1, transform: 'scale(1)' }
        }
      }}>
        <Typography variant="h4" fontWeight={700} color="#fff" gutterBottom align="center">
          Корзина
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          Проверьте ваш заказ и оформите доставку.
        </Typography>
        <List sx={{ background: 'rgba(255,255,255,0.06)', borderRadius: 3, mb: 2 }}>
          <ListItem secondaryAction={<Button variant="contained" size="small">Удалить</Button>}>
            <ListItemText primary="Пицца Маргарита" secondary="1 шт. — 350 ₽" sx={{ color: '#fff' }} />
          </ListItem>
          <ListItem secondaryAction={<Button variant="contained" size="small">Удалить</Button>}>
            <ListItemText primary="Салат Цезарь" secondary="2 шт. — 500 ₽" sx={{ color: '#fff' }} />
          </ListItem>
        </List>
        <Button fullWidth size="large" variant="contained" sx={{
          background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
          color: '#fff',
          fontWeight: 600,
          fontSize: 18,
          py: 1.5,
          borderRadius: 3,
          boxShadow: '0 2px 16px 0 rgba(33,147,176,0.10)',
          transition: 'all 0.3s cubic-bezier(.4,2,.6,1)',
          '&:hover': {
            background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
            transform: 'scale(1.06)',
            boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
          }
        }}>
          Оформить заказ
        </Button>
      </Paper>
    </Box>
  );
};

export default FoodCartPage; 