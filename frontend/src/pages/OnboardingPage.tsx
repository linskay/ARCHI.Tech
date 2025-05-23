import React, { useState } from 'react';
import {
  Box,
  Container,
  Button,
  Typography,
  useTheme,
  useMediaQuery,
  IconButton,
} from '@mui/material';
import { motion, AnimatePresence } from 'framer-motion';
import { Link, useNavigate } from 'react-router-dom';
import {
  ArrowForward as ArrowForwardIcon,
  ArrowBack as ArrowBackIcon,
} from '@mui/icons-material';

interface Slide {
  title: string;
  description: string;
  image: string;
  color: string;
}

const slides: Slide[] = [
  {
    title: 'Добро пожаловать в ARCHI.Tech',
    description:
      'Ваше современное рабочее пространство для продуктивности и совместной работы.',
    image: '/images/onboarding-1.svg',
    color: '#1a237e',
  },
  {
    title: 'Умное управление рабочим пространством',
    description:
      'Легко бронируйте рабочие места, переговорные комнаты и управляйте своим расписанием.',
    image: '/images/onboarding-2.svg',
    color: '#0d47a1',
  },
  {
    title: 'Сотрудничество в реальном времени',
    description:
      'Общайтесь с командой и работайте вместе без проблем.',
    image: '/images/onboarding-3.svg',
    color: '#1565c0',
  },
  {
    title: 'Расширенная аналитика',
    description:
      'Отслеживайте использование рабочего пространства и оптимизируйте свою продуктивность.',
    image: '/images/onboarding-4.svg',
    color: '#1976d2',
  },
  {
    title: 'Начнем',
    description:
      'Присоединяйтесь к нашему сообществу профессионалов и преобразите свой опыт работы.',
    image: '/images/onboarding-5.svg',
    color: '#2196f3',
  },
];

const OnboardingPage: React.FC = () => {
  const [currentSlide, setCurrentSlide] = useState(0);
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));
  const navigate = useNavigate();

  const handleNext = () => {
    if (currentSlide < slides.length - 1) {
      setCurrentSlide((prev) => prev + 1);
    }
  };

  const handleBack = () => {
    if (currentSlide > 0) {
      setCurrentSlide((prev) => prev - 1);
    }
  };

  const handleSkip = () => {
    localStorage.setItem('hasCompletedOnboarding', 'true');
    navigate('/');
  };

  const slideVariants = {
    enter: (direction: number) => ({
      x: direction > 0 ? 1000 : -1000,
      opacity: 0,
    }),
    center: {
      zIndex: 1,
      x: 0,
      opacity: 1,
    },
    exit: (direction: number) => ({
      zIndex: 0,
      x: direction < 0 ? 1000 : -1000,
      opacity: 0,
    }),
  };

  const swipeConfidenceThreshold = 10000;
  const swipePower = (offset: number, velocity: number) => {
    return Math.abs(offset) * velocity;
  };

  return (
    <Box
      sx={{
        minHeight: '100vh',
        display: 'flex',
        flexDirection: 'column',
        background: `linear-gradient(135deg, ${slides[currentSlide].color} 0%, ${theme.palette.primary.dark} 100%)`,
      }}
    >
      <Container
        maxWidth="lg"
        sx={{
          flex: 1,
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          position: 'relative',
        }}
      >
        <Box sx={{ position: 'absolute', top: 20, right: 20 }}>
          <Button 
            variant="text" 
            sx={{ color: 'white' }}
            onClick={handleSkip}
          >
            ПРОПУСТИТЬ
          </Button>
        </Box>

        <AnimatePresence initial={false} custom={currentSlide}>
          <motion.div
            key={currentSlide}
            custom={currentSlide}
            variants={slideVariants}
            initial="enter"
            animate="center"
            exit="exit"
            transition={{
              x: { type: 'spring', stiffness: 300, damping: 30 },
              opacity: { duration: 0.2 },
            }}
            drag="x"
            dragConstraints={{ left: 0, right: 0 }}
            dragElastic={1}
            onDragEnd={(e, { offset, velocity }) => {
              const swipe = swipePower(offset.x, velocity.x);

              if (swipe < -swipeConfidenceThreshold) {
                handleNext();
              } else if (swipe > swipeConfidenceThreshold) {
                handleBack();
              }
            }}
            style={{
              position: 'absolute',
              width: '100%',
              height: '100%',
              display: 'flex',
              flexDirection: 'column',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            <Box
              sx={{
                textAlign: 'center',
                color: 'white',
                maxWidth: 600,
                mx: 'auto',
                px: 2,
              }}
            >
              <motion.div
                initial={{ scale: 0.8, opacity: 0 }}
                animate={{ scale: 1, opacity: 1 }}
                transition={{ delay: 0.2 }}
              >
                <Typography
                  variant="h3"
                  component="h1"
                  gutterBottom
                  sx={{
                    fontWeight: 700,
                    mb: 3,
                  }}
                >
                  {slides[currentSlide].title}
                </Typography>
              </motion.div>

              <motion.div
                initial={{ y: 20, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ delay: 0.4 }}
              >
                <Typography
                  variant="h6"
                  sx={{
                    mb: 4,
                    opacity: 0.9,
                  }}
                >
                  {slides[currentSlide].description}
                </Typography>
              </motion.div>

              <motion.div
                initial={{ y: 40, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                transition={{ delay: 0.6 }}
              >
                <Box
                  component="img"
                  src={slides[currentSlide].image}
                  alt={slides[currentSlide].title}
                  sx={{
                    width: '100%',
                    maxWidth: 400,
                    height: 'auto',
                    mb: 4,
                  }}
                />
              </motion.div>
            </Box>
          </motion.div>
        </AnimatePresence>

        {/* Navigation */}
        <Box
          sx={{
            position: 'absolute',
            bottom: 40,
            left: 0,
            right: 0,
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            gap: 2,
          }}
        >
          <IconButton
            onClick={handleBack}
            disabled={currentSlide === 0}
            sx={{ color: 'white' }}
          >
            <ArrowBackIcon />
          </IconButton>

          <Box sx={{ display: 'flex', gap: 1 }}>
            {slides.map((_, index) => (
              <Box
                key={index}
                sx={{
                  width: 8,
                  height: 8,
                  borderRadius: '50%',
                  bgcolor: index === currentSlide ? 'white' : 'rgba(255, 255, 255, 0.5)',
                  transition: 'bgcolor 0.3s ease',
                }}
              />
            ))}
          </Box>

          <IconButton
            onClick={handleNext}
            disabled={currentSlide === slides.length - 1}
            sx={{ color: 'white' }}
          >
            <ArrowForwardIcon />
          </IconButton>
        </Box>

        {/* Finish Button */}
        {currentSlide === slides.length - 1 && (
          <Box
            sx={{
              position: 'absolute',
              bottom: 100,
              left: 0,
              right: 0,
              textAlign: 'center',
            }}
          >
            <motion.div
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.5 }}
            >
              <Button
                variant="contained"
                size="large"
                onClick={handleSkip}
                sx={{
                  borderRadius: 2,
                  py: 1.5,
                  background: 'linear-gradient(45deg, #2196f3, #2193b0)',
                  '&:hover': {
                    background: 'linear-gradient(45deg, #2193b0, #2196f3)',
                  },
                }}
              >
                Начать работу
              </Button>
            </motion.div>
          </Box>
        )}
      </Container>
    </Box>
  );
};

export default OnboardingPage; 