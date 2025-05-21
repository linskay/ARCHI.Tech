import React from 'react';
import { Box, Typography, Paper, Switch, FormControlLabel } from '@mui/material';

const NotificationsPage: React.FC = () => {
  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 6,
        borderRadius: 6,
        background: 'linear-gradient(135deg, #2193b0 0%, #6dd5ed 100%)',
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
          Уведомления
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          Управляйте настройками уведомлений для вашего аккаунта.
        </Typography>
        <FormControlLabel
          control={<Switch defaultChecked color="primary" />}
          label={<Typography color="#fff">Email-уведомления</Typography>}
          sx={{ mb: 2 }}
        />
        <FormControlLabel
          control={<Switch color="primary" />}
          label={<Typography color="#fff">Push-уведомления</Typography>}
        />
      </Paper>
    </Box>
  );
};

export default NotificationsPage; 