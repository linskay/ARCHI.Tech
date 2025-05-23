import React from 'react';
import { Box, Typography, Container, Paper } from '@mui/material';
import { motion } from 'framer-motion';

const NotificationsPage: React.FC = () => {
  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <Paper elevation={3} sx={{ p: 4, borderRadius: 2 }}>
          <Typography variant="h4" component="h1" gutterBottom>
            Личный кабинет
          </Typography>
          <Typography variant="h6" gutterBottom>
            Управление уведомлениями
          </Typography>
          <Box sx={{ mt: 3 }}>
            {/* Здесь будут настройки уведомлений */}
            <Typography>Содержимое страницы управления уведомлениями...</Typography>
          </Box>
        </Paper>
      </motion.div>
    </Container>
  );
};

export default NotificationsPage; 