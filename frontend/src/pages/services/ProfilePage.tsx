import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Paper,
  Button,
  Avatar,
  TextField,
  Grid,
  Switch,
  FormControlLabel,
  IconButton,
  Divider,
  CircularProgress,
  Alert,
} from '@mui/material';
import { PhotoCamera, Save, Edit } from '@mui/icons-material';
import { UserProfile, UpdateProfileData } from '../../types/user';
import { userService } from '../../services/userService';

const ProfilePage: React.FC = () => {
  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState<UpdateProfileData>({
    firstName: '',
    lastName: '',
    phone: '',
  });
  const [avatarFile, setAvatarFile] = useState<File | null>(null);

  useEffect(() => {
    loadProfile();
  }, []);

  const loadProfile = async () => {
    try {
      const data = await userService.getProfile();
      setProfile(data);
      setFormData({
        firstName: data.firstName,
        lastName: data.lastName,
        phone: data.phone || '',
      });
    } catch (err) {
      setError('Не удалось загрузить профиль');
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleAvatarChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      setAvatarFile(e.target.files[0]);
    }
  };

  const handleNotificationChange = async (type: 'email' | 'push', checked: boolean) => {
    if (!profile) return;
    try {
      const updatedProfile = await userService.updateNotifications({
        ...profile.notifications,
        [type]: checked,
      });
      setProfile(updatedProfile);
    } catch (err) {
      setError('Не удалось обновить настройки уведомлений');
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const updateData: UpdateProfileData = {
        ...formData,
        avatar: avatarFile || undefined,
      };
      const updatedProfile = await userService.updateProfile(updateData);
      setProfile(updatedProfile);
      setIsEditing(false);
      setAvatarFile(null);
    } catch (err) {
      setError('Не удалось обновить профиль');
    }
  };

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  if (!profile) {
    return (
      <Box sx={{ p: 3 }}>
        <Alert severity="error">Профиль не найден</Alert>
      </Box>
    );
  }

  return (
    <Box sx={{ p: 3, maxWidth: 800, mx: 'auto' }}>
      <Paper elevation={6} sx={{
        p: 4,
        borderRadius: 4,
        background: 'linear-gradient(135deg, #232526 0%, #414345 100%)',
        boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
      }}>
        {error && (
          <Alert severity="error" sx={{ mb: 3 }} onClose={() => setError(null)}>
            {error}
          </Alert>
        )}

        <Box sx={{ display: 'flex', alignItems: 'center', mb: 4 }}>
          <Box sx={{ position: 'relative', mr: 3 }}>
            <Avatar
              src={profile.avatar}
              sx={{ width: 100, height: 100, border: '3px solid #2193b0' }}
            />
            {isEditing && (
              <IconButton
                sx={{
                  position: 'absolute',
                  bottom: 0,
                  right: 0,
                  bgcolor: '#2193b0',
                  '&:hover': { bgcolor: '#6dd5ed' },
                }}
                component="label"
              >
                <input
                  type="file"
                  hidden
                  accept="image/*"
                  onChange={handleAvatarChange}
                />
                <PhotoCamera sx={{ color: '#fff' }} />
              </IconButton>
            )}
          </Box>
          <Box>
            <Typography variant="h4" color="#fff" gutterBottom>
              {profile.firstName} {profile.lastName}
            </Typography>
            <Typography variant="subtitle1" color="#e3f6fc">
              {profile.email}
            </Typography>
          </Box>
        </Box>

        <Divider sx={{ my: 3, bgcolor: 'rgba(255,255,255,0.1)' }} />

        {isEditing ? (
          <form onSubmit={handleSubmit}>
            <Box sx={{ display: 'flex', flexDirection: { xs: 'column', sm: 'row' }, gap: 2, mb: 2 }}>
              <TextField
                fullWidth
                label="Имя"
                name="firstName"
                value={formData.firstName}
                onChange={handleInputChange}
              />
              <TextField
                fullWidth
                label="Фамилия"
                name="lastName"
                value={formData.lastName}
                onChange={handleInputChange}
              />
            </Box>
            <TextField
              fullWidth
              label="Телефон"
              name="phone"
              value={formData.phone}
              onChange={handleInputChange}
              sx={{ mb: 2 }}
            />
            <Box sx={{ mt: 3, display: 'flex', gap: 2 }}>
              <Button
                type="submit"
                variant="contained"
                startIcon={<Save />}
                sx={{
                  background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
                  '&:hover': {
                    background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
                  },
                }}
              >
                Сохранить
              </Button>
              <Button
                variant="outlined"
                onClick={() => setIsEditing(false)}
                sx={{ color: '#e3f6fc', borderColor: '#e3f6fc' }}
              >
                Отмена
              </Button>
            </Box>
          </form>
        ) : (
          <>
            <Box sx={{ mb: 4 }}>
              <Typography variant="h6" color="#fff" gutterBottom>
                Контактная информация
              </Typography>
              <Typography color="#e3f6fc" paragraph>
                Телефон: {profile.phone || 'Не указан'}
              </Typography>
            </Box>

            <Box sx={{ mb: 4 }}>
              <Typography variant="h6" color="#fff" gutterBottom>
                Уведомления
              </Typography>
              <FormControlLabel
                control={
                  <Switch
                    checked={profile.notifications.email}
                    onChange={(e) => handleNotificationChange('email', e.target.checked)}
                    color="primary"
                  />
                }
                label={<Typography color="#e3f6fc">Email-уведомления</Typography>}
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={profile.notifications.push}
                    onChange={(e) => handleNotificationChange('push', e.target.checked)}
                    color="primary"
                  />
                }
                label={<Typography color="#e3f6fc">Push-уведомления</Typography>}
              />
            </Box>

            <Button
              variant="contained"
              startIcon={<Edit />}
              onClick={() => setIsEditing(true)}
              sx={{
                background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
                '&:hover': {
                  background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
                },
              }}
            >
              Редактировать профиль
            </Button>
          </>
        )}
      </Paper>
    </Box>
  );
};

export default ProfilePage; 