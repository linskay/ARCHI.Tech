import React from 'react';
import { Box, Typography, Paper, List, ListItem, ListItemText } from '@mui/material';

const MyParkingPage: React.FC = () => {
  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 6,
        borderRadius: 6,
        background: 'linear-gradient(135deg, #6dd5ed 0%, #2193b0 100%)',
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
          Мои бронирования парковки
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          Здесь отображаются ваши активные и прошедшие бронирования парковочных мест.
        </Typography>
        <List sx={{ background: 'rgba(255,255,255,0.06)', borderRadius: 3 }}>
          <ListItem>
            <ListItemText primary="Парковка №12" secondary="21 мая 2025, 09:00 - 19:00" sx={{ color: '#fff' }} />
          </ListItem>
          <ListItem>
            <ListItemText primary="Парковка №7" secondary="22 мая 2025, 10:00 - 18:00" sx={{ color: '#fff' }} />
          </ListItem>
        </List>
      </Paper>
    </Box>
  );
};

export default MyParkingPage; 