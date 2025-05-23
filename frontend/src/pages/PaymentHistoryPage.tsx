import React from 'react';
import { Box, Typography, Container, Paper } from '@mui/material';
import { motion } from 'framer-motion';

const PaymentHistoryPage: React.FC = () => {
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
            История платежей
          </Typography>
          <Box sx={{ mt: 3 }}>
            {/* Здесь будет таблица с историей платежей */}
            <Typography>Содержимое страницы истории платежей...</Typography>
          </Box>
        </Paper>
      </motion.div>
    </Container>
  );
};

export default PaymentHistoryPage; 