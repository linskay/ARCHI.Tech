import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const WorkspaceMapPage: React.FC = () => {
  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 6,
        borderRadius: 6,
        background: 'linear-gradient(135deg, #2193b0 0%, #6dd5ed 100%)',
        boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
        animation: 'fadeIn 1.2s cubic-bezier(.4,2,.6,1)',
        maxWidth: 600,
        width: '100%',
        '@keyframes fadeIn': {
          from: { opacity: 0, transform: 'scale(0.95)' },
          to: { opacity: 1, transform: 'scale(1)' }
        }
      }}>
        <Typography variant="h4" fontWeight={700} color="#fff" gutterBottom align="center">
          Карта коворкинга
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          Здесь будет интерактивная карта для выбора рабочего места.
        </Typography>
        <Box sx={{ width: '100%', height: 300, background: 'rgba(255,255,255,0.08)', borderRadius: 4, display: 'flex', alignItems: 'center', justifyContent: 'center', color: '#fff', fontSize: 32, fontWeight: 600, letterSpacing: 2, boxShadow: '0 2px 16px 0 rgba(33,147,176,0.10)', mb: 2 }}>
          [Интерактивная карта]
        </Box>
      </Paper>
    </Box>
  );
};

export default WorkspaceMapPage; 