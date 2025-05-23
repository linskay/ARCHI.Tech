import React, { useState, useEffect } from 'react';
import { Box, Typography, Container, Paper, Grid, TextField, Button, Avatar, CircularProgress, Alert, Snackbar } from '@mui/material';
import { motion } from 'framer-motion';

const ProfilePage: React.FC = () => {
  const [userData, setUserData] = useState<{ name: string; email: string } | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [editedName, setEditedName] = useState('');
  const [isSaving, setIsSaving] = useState(false);
  const [saveError, setSaveError] = useState<string | null>(null);
  const [saveSuccess, setSaveSuccess] = useState(false);

  useEffect(() => {
    // Simulate fetching user data from an API
    const fetchUserData = async () => {
      setLoading(true);
      setError(null);
      try {
        // TODO: Replace with actual API call to fetch user data
        const simulatedUserData = {
          name: 'Пример Пользователя',
          email: 'user@example.com',
          // Add other fields fetched from API
        };
        // Simulate network delay
        await new Promise(resolve => setTimeout(resolve, 1000));
        setUserData(simulatedUserData);
        setEditedName(simulatedUserData.name); // Set initial value for editing
      } catch (err) {
        console.error('Failed to fetch user data:', err);
        setError('Не удалось загрузить данные пользователя.');
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, []); // Empty dependency array means this effect runs once on mount

  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEditedName(event.target.value);
    setSaveSuccess(false); // Reset success message on change
    setSaveError(null); // Reset error message on change
  };

  const handleSaveChanges = async () => {
    setIsSaving(true);
    setSaveError(null);
    setSaveSuccess(false);

    try {
      // TODO: Replace with actual API call to save user data
      console.log('Simulating saving changes:', { ...userData, name: editedName });
      // Simulate API call delay
      await new Promise(resolve => setTimeout(resolve, 1500));

      // Simulate success
      setUserData(prevData => prevData ? { ...prevData, name: editedName } : null); // Update local state with saved name
      setSaveSuccess(true);

      // Simulate error (uncomment to test error state)
      // throw new Error('Simulated save error');

    } catch (err) {
      console.error('Failed to save user data:', err);
      setSaveError('Не удалось сохранить изменения.');
    } finally {
      setIsSaving(false);
    }
  };

  const handleCloseSnackbar = (event?: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === 'clickaway') {
      return;
    }
    setSaveSuccess(false);
    setSaveError(null);
  };

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

          {loading && (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
              <CircularProgress />
            </Box>
          )}

          {error && (
            <Alert severity="error" sx={{ mt: 4 }}>
              {error}
            </Alert>
          )}

          {!loading && !error && userData && (
            <Box sx={{ mb: 4 }}>
              <Typography variant="h6" gutterBottom>
                Профиль (личные данные, фото)
              </Typography>

              <Grid container spacing={3}>
                <Grid item xs={12} md={4} sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                  {/* Секция фото профиля */}
                  {/* TODO: Реализовать логику загрузки фото профиля и отображения */}
                  <Avatar
                    alt={userData.name}
                    src={'/path/to/default/avatar.png'} // Временное изображение
                    sx={{ width: 120, height: 120, mb: 2 }}
                  />
                  <Button variant="outlined" component="label">
                    Загрузить фото
                    <input type="file" hidden />
                  </Button>
                </Grid>
                <Grid item xs={12} md={8}>
                  {/* Форма личных данных */}
                  <Typography variant="h6" gutterBottom>Личные данные</Typography>
                  <TextField
                    fullWidth
                    label="Имя"
                    value={editedName}
                    onChange={handleNameChange}
                    margin="normal"
                  />
                  <TextField
                    fullWidth
                    label="Email"
                    value={userData.email}
                    margin="normal"
                    disabled // Email часто не редактируется
                  />
                  {/* TODO: Добавьте другие поля полей */}

                  <Button
                    variant="contained"
                    sx={{ mt: 3 }}
                    onClick={handleSaveChanges}
                    disabled={isSaving} // Отключаем кнопку во время сохранения
                  >
                    {isSaving ? <CircularProgress size={24} color="inherit" /> : 'Сохранить изменения'}
                  </Button>
                </Grid>
              </Grid>
            </Box>
          )}

          {/* Placeholder для других разделов личного кабинета */}
          <Box sx={{ mt: 4 }}>
            <Typography variant="h6" gutterBottom>Другие разделы</Typography>
            {/* Здесь могут быть ссылки или кнопки на другие страницы личного кабинета */}
            <Typography>Ссылки на Тарифы, Привязка карт, История платежей, Уведомления, Настройки...</Typography>
          </Box>

        </Paper>
      </motion.div>

      {/* Snackbar для сообщений об успехе или ошибке сохранения */}
      <Snackbar open={saveSuccess} autoHideDuration={6000} onClose={handleCloseSnackbar}>
        <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
          Изменения успешно сохранены!
        </Alert>
      </Snackbar>

      <Snackbar open={!!saveError} autoHideDuration={6000} onClose={handleCloseSnackbar}>
        <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
          {saveError}
        </Alert>
      </Snackbar>

    </Container>
  );
};

export default ProfilePage; 