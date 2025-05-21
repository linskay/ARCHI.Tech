import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Paper,
  Avatar,
  TextField,
  Button,
  Grid,
  Switch,
  FormControlLabel,
  Divider,
  IconButton,
  Snackbar,
  Alert,
  CircularProgress
} from '@mui/material';
import { Edit as EditIcon, Save as SaveIcon, Cancel as CancelIcon } from '@mui/icons-material';
import { userService } from '../../services/userService';
import { UserProfile, UpdateProfileData } from '../../types/user';

interface NotificationSettings {
  email: boolean;
  push: boolean;
  sms: boolean;
}

const ProfilePage: React.FC = () => {
  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const [formData, setFormData] = useState<UpdateProfileData>({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    notifications: {
      email: true,
      push: true,
      sms: false
    }
  });

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      setLoading(true);
      const data = await userService.getProfile();
      setProfile(data);
      setFormData({
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        phone: data.phone || '',
        notifications: {
          email: data.notifications.email,
          push: data.notifications.push,
          sms: data.notifications.sms
        }
      });
    } catch (err) {
      setError('Не удалось загрузить профиль');
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancel = () => {
    setIsEditing(false);
    if (profile) {
      setFormData({
        firstName: profile.firstName,
        lastName: profile.lastName,
        email: profile.email,
        phone: profile.phone || '',
        notifications: {
          email: profile.notifications.email,
          push: profile.notifications.push,
          sms: profile.notifications.sms
        }
      });
    }
  };

  const handleSave = async () => {
    try {
      setLoading(true);
      await userService.updateProfile(formData);
      await fetchProfile();
      setIsEditing(false);
      setSuccess('Профиль успешно обновлен');
    } catch (err) {
      setError('Не удалось обновить профиль');
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleNotificationChange = (name: keyof NotificationSettings) => (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData(prev => ({
      ...prev,
      notifications: {
        ...prev.notifications,
        [name]: e.target.checked
      }
    }));
  };

  if (loading && !profile) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box sx={{ minHeight: '100vh', p: 3, background: 'none' }}>
      <Paper elevation={6} sx={{
        p: 4,
        borderRadius: 4,
        background: 'linear-gradient(135deg, #232526 0%, #414345 100%)',
        boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
        maxWidth: 800,
        mx: 'auto'
      }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
          <Typography variant="h4" fontWeight={700} color="#fff">
            Профиль
          </Typography>
          {!isEditing ? (
            <IconButton onClick={handleEdit} color="primary" sx={{ color: '#fff' }}>
              <EditIcon />
            </IconButton>
          ) : (
            <Box>
              <IconButton onClick={handleSave} color="primary" sx={{ color: '#fff', mr: 1 }}>
                <SaveIcon />
              </IconButton>
              <IconButton onClick={handleCancel} color="error" sx={{ color: '#fff' }}>
                <CancelIcon />
              </IconButton>
            </Box>
          )}
        </Box>

        <Grid container spacing={3}>
          <Grid item xs={12} md={4}>
            <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
              <Avatar
                sx={{
                  width: 120,
                  height: 120,
                  mb: 2,
                  boxShadow: '0 4px 16px 0 rgba(33,147,176,0.15)'
                }}
                src={profile?.avatar}
              />
              <Typography variant="h6" color="#fff" align="center">
                {profile?.firstName} {profile?.lastName}
              </Typography>
            </Box>
          </Grid>

          <Grid item xs={12} md={8}>
            <Box sx={{ mb: 3 }}>
              <Typography variant="h6" color="#fff" gutterBottom>
                Личная информация
              </Typography>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Имя"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleInputChange}
                    disabled={!isEditing}
                    sx={{
                      '& .MuiOutlinedInput-root': {
                        color: '#fff',
                        '& fieldset': { borderColor: 'rgba(255,255,255,0.23)' },
                        '&:hover fieldset': { borderColor: 'rgba(255,255,255,0.5)' }
                      },
                      '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' }
                    }}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Фамилия"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleInputChange}
                    disabled={!isEditing}
                    sx={{
                      '& .MuiOutlinedInput-root': {
                        color: '#fff',
                        '& fieldset': { borderColor: 'rgba(255,255,255,0.23)' },
                        '&:hover fieldset': { borderColor: 'rgba(255,255,255,0.5)' }
                      },
                      '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' }
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    label="Email"
                    name="email"
                    value={formData.email}
                    onChange={handleInputChange}
                    disabled={!isEditing}
                    sx={{
                      '& .MuiOutlinedInput-root': {
                        color: '#fff',
                        '& fieldset': { borderColor: 'rgba(255,255,255,0.23)' },
                        '&:hover fieldset': { borderColor: 'rgba(255,255,255,0.5)' }
                      },
                      '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' }
                    }}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    label="Телефон"
                    name="phone"
                    value={formData.phone}
                    onChange={handleInputChange}
                    disabled={!isEditing}
                    sx={{
                      '& .MuiOutlinedInput-root': {
                        color: '#fff',
                        '& fieldset': { borderColor: 'rgba(255,255,255,0.23)' },
                        '&:hover fieldset': { borderColor: 'rgba(255,255,255,0.5)' }
                      },
                      '& .MuiInputLabel-root': { color: 'rgba(255,255,255,0.7)' }
                    }}
                  />
                </Grid>
              </Grid>
            </Box>

            <Divider sx={{ my: 3, borderColor: 'rgba(255,255,255,0.12)' }} />

            <Box>
              <Typography variant="h6" color="#fff" gutterBottom>
                Уведомления
              </Typography>
              <FormControlLabel
                control={
                  <Switch
                    checked={formData.notifications.email}
                    onChange={handleNotificationChange('email')}
                    disabled={!isEditing}
                  />
                }
                label="Email уведомления"
                sx={{ color: '#fff' }}
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={formData.notifications.push}
                    onChange={handleNotificationChange('push')}
                    disabled={!isEditing}
                  />
                }
                label="Push уведомления"
                sx={{ color: '#fff' }}
              />
              <FormControlLabel
                control={
                  <Switch
                    checked={formData.notifications.sms}
                    onChange={handleNotificationChange('sms')}
                    disabled={!isEditing}
                  />
                }
                label="SMS уведомления"
                sx={{ color: '#fff' }}
              />
            </Box>
          </Grid>
        </Grid>
      </Paper>

      <Snackbar
        open={!!error}
        autoHideDuration={6000}
        onClose={() => setError(null)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert severity="error" onClose={() => setError(null)}>
          {error}
        </Alert>
      </Snackbar>

      <Snackbar
        open={!!success}
        autoHideDuration={6000}
        onClose={() => setSuccess(null)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Alert severity="success" onClose={() => setSuccess(null)}>
          {success}
        </Alert>
      </Snackbar>
    </Box>
  );
};

export default ProfilePage; 