import React, { useState } from 'react';
import { 
  Box, 
  Typography, 
  Paper, 
  Button, 
  Card, 
  CardMedia, 
  CardContent,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Chip,
  IconButton
} from '@mui/material';
import { 
  Wifi, 
  Power, 
  Coffee, 
  MeetingRoom, 
  Security, 
  AccessTime,
  ArrowBack,
  ArrowForward
} from '@mui/icons-material';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider, DateCalendar } from '@mui/x-date-pickers';
import { ru } from 'date-fns/locale';

const WorkspaceDetailsPage: React.FC = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const [selectedDate, setSelectedDate] = useState<Date | null>(new Date());

  const images = [
    '/images/workspace1.jpg',
    '/images/workspace2.jpg',
    '/images/workspace3.jpg',
  ];

  const features = [
    { icon: <Wifi />, text: 'Высокоскоростной Wi-Fi' },
    { icon: <Power />, text: 'Розетки и USB-порты' },
    { icon: <Coffee />, text: 'Кофе и вода включены' },
    { icon: <MeetingRoom />, text: 'Доступ к переговорным' },
    { icon: <Security />, text: 'Круглосуточная охрана' },
    { icon: <AccessTime />, text: '24/7 доступ' },
  ];

  const handlePrevImage = () => {
    setCurrentImageIndex((prev) => (prev === 0 ? images.length - 1 : prev - 1));
  };

  const handleNextImage = () => {
    setCurrentImageIndex((prev) => (prev === images.length - 1 ? 0 : prev + 1));
  };

  return (
    <Box sx={{ minHeight: '100vh', py: 4, px: 2, background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 4,
        borderRadius: 6,
        background: 'linear-gradient(135deg, #232526 0%, #414345 100%)',
        boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
        animation: 'fadeIn 1.2s cubic-bezier(.4,2,.6,1)',
        maxWidth: 1200,
        margin: '0 auto',
        '@keyframes fadeIn': {
          from: { opacity: 0, transform: 'scale(0.95)' },
          to: { opacity: 1, transform: 'scale(1)' }
        }
      }}>
        <Box sx={{ display: 'flex', flexDirection: { xs: 'column', md: 'row' }, gap: 4 }}>
          {/* Галерея */}
          <Box sx={{ flex: 1 }}>
            <Box sx={{ position: 'relative', borderRadius: 4, overflow: 'hidden' }}>
              <CardMedia
                component="img"
                height="400"
                image={images[currentImageIndex]}
                alt="Рабочее место"
                sx={{ borderRadius: 4 }}
              />
              <IconButton
                onClick={handlePrevImage}
                sx={{
                  position: 'absolute',
                  left: 8,
                  top: '50%',
                  transform: 'translateY(-50%)',
                  bgcolor: 'rgba(0,0,0,0.5)',
                  color: 'white',
                  '&:hover': { bgcolor: 'rgba(0,0,0,0.7)' }
                }}
              >
                <ArrowBack />
              </IconButton>
              <IconButton
                onClick={handleNextImage}
                sx={{
                  position: 'absolute',
                  right: 8,
                  top: '50%',
                  transform: 'translateY(-50%)',
                  bgcolor: 'rgba(0,0,0,0.5)',
                  color: 'white',
                  '&:hover': { bgcolor: 'rgba(0,0,0,0.7)' }
                }}
              >
                <ArrowForward />
              </IconButton>
            </Box>
          </Box>

          {/* Информация */}
          <Box sx={{ flex: 1 }}>
            <Typography variant="h4" fontWeight={700} color="#fff" gutterBottom>
              Рабочее место "Премиум"
            </Typography>
            <Typography variant="subtitle1" color="#e3f6fc" paragraph>
              Просторное рабочее место с панорамным видом на город. Идеально подходит для фрилансеров и предпринимателей.
            </Typography>

            <List sx={{ mb: 3 }}>
              {features.map((feature, index) => (
                <ListItem key={index} sx={{ py: 1 }}>
                  <ListItemIcon sx={{ color: '#6dd5ed' }}>
                    {feature.icon}
                  </ListItemIcon>
                  <ListItemText primary={feature.text} sx={{ color: '#e3f6fc' }} />
                </ListItem>
              ))}
            </List>

            <Divider sx={{ my: 3, bgcolor: 'rgba(255,255,255,0.1)' }} />

            <Box sx={{ mb: 3 }}>
              <Typography variant="h6" color="#fff" gutterBottom>
                Стоимость
              </Typography>
              <Box sx={{ display: 'flex', alignItems: 'baseline', gap: 2 }}>
                <Typography variant="h4" color="#6dd5ed" fontWeight={700}>
                  1 500 ₽
                </Typography>
                <Typography variant="subtitle1" color="#e3f6fc">
                  / день
                </Typography>
              </Box>
            </Box>

            <Button
              fullWidth
              size="large"
              variant="contained"
              sx={{
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
                  transform: 'scale(1.02)',
                  boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
                }
              }}
            >
              Забронировать
            </Button>
          </Box>
        </Box>

        {/* Календарь */}
        <Box sx={{ mt: 4 }}>
          <Paper sx={{ p: 3, bgcolor: 'rgba(255,255,255,0.05)', borderRadius: 4 }}>
            <Typography variant="h6" color="#fff" gutterBottom>
              Выберите дату
            </Typography>
            <LocalizationProvider dateAdapter={AdapterDateFns} adapterLocale={ru}>
              <DateCalendar
                value={selectedDate}
                onChange={(newValue: Date | null) => setSelectedDate(newValue)}
                sx={{
                  '& .MuiPickersCalendarHeader-root': {
                    color: '#fff',
                  },
                  '& .MuiPickersDay-root': {
                    color: '#e3f6fc',
                    '&.Mui-selected': {
                      background: '#2193b0',
                      '&:hover': {
                        background: '#6dd5ed',
                      },
                    },
                  },
                }}
              />
            </LocalizationProvider>
          </Paper>
        </Box>
      </Paper>
    </Box>
  );
};

export default WorkspaceDetailsPage; 