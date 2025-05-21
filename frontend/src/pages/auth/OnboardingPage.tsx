import React, { useState } from 'react';
import { Box, Typography, Paper, Button, MobileStepper } from '@mui/material';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';

const slides = [
  {
    title: 'Добро пожаловать в Коворкинг!',
    desc: 'Откройте для себя новые возможности для работы, общения и развития.'
  },
  {
    title: 'Бронируйте рабочие места',
    desc: 'Выбирайте удобные места и бронируйте их в пару кликов.'
  },
  {
    title: 'Управляйте подпиской',
    desc: 'Следите за тарифами, платежами и бонусами прямо в приложении.'
  },
  {
    title: 'Пользуйтесь дополнительными сервисами',
    desc: 'Гостевой доступ, парковка, еда, клининг, оргтехника и многое другое.'
  },
  {
    title: 'Всегда на связи',
    desc: 'Поддержка, уведомления и чат — всё для вашего комфорта.'
  }
];

const OnboardingPage: React.FC = () => {
  const [activeStep, setActiveStep] = useState(0);

  const handleNext = () => setActiveStep((prev) => Math.min(prev + 1, slides.length - 1));
  const handleBack = () => setActiveStep((prev) => Math.max(prev - 1, 0));

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
          {slides[activeStep].title}
        </Typography>
        <Typography variant="subtitle1" color="#e3f6fc" align="center" sx={{ mb: 4 }}>
          {slides[activeStep].desc}
        </Typography>
        <MobileStepper
          variant="dots"
          steps={slides.length}
          position="static"
          activeStep={activeStep}
          sx={{ background: 'transparent', mb: 2, justifyContent: 'center' }}
          nextButton={
            <Button size="small" onClick={handleNext} disabled={activeStep === slides.length - 1} sx={{ color: '#fff' }}>
              Далее
              <KeyboardArrowRight />
            </Button>
          }
          backButton={
            <Button size="small" onClick={handleBack} disabled={activeStep === 0} sx={{ color: '#fff' }}>
              <KeyboardArrowLeft />
              Назад
            </Button>
          }
        />
        <Button fullWidth size="large" variant="contained" sx={{
          background: 'linear-gradient(90deg, #232526 0%, #414345 100%)',
          color: '#fff',
          fontWeight: 600,
          fontSize: 18,
          py: 1.5,
          borderRadius: 3,
          boxShadow: '0 2px 16px 0 rgba(33,147,176,0.10)',
          transition: 'all 0.3s cubic-bezier(.4,2,.6,1)',
          mt: 2,
          '&:hover': {
            background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
            transform: 'scale(1.06)',
            boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
          }
        }} disabled={activeStep !== slides.length - 1}>
          Начать пользоваться
        </Button>
      </Paper>
    </Box>
  );
};

export default OnboardingPage; 