import React from 'react';
import { Box, Typography, Paper, Stepper, Step, StepLabel } from '@mui/material';

const steps = ['Бронирование подтверждено', 'Переговорная готова', 'Встреча завершена'];

const MeetingRoomStatusPage: React.FC = () => {
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
          Статус бронирования
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          Следите за статусом вашего бронирования переговорной.
        </Typography>
        <Stepper activeStep={1} alternativeLabel sx={{ mb: 2 }}>
          {steps.map((label) => (
            <Step key={label}>
              <StepLabel>{label}</StepLabel>
            </Step>
          ))}
        </Stepper>
      </Paper>
    </Box>
  );
};

export default MeetingRoomStatusPage; 