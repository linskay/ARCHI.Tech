import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider, createTheme, CssBaseline, Container, GlobalStyles, Box, Typography } from '@mui/material';
import { ruRU } from '@mui/material/locale';
import Navigation from './components/Navigation';
import Workspaces from './pages/Workspaces';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ForgotPasswordPage from './pages/ForgotPasswordPage';
import EmailVerificationPage from './pages/EmailVerificationPage';
import OnboardingPage from './pages/OnboardingPage';
import HomePage from './pages/HomePage';
import ProfilePage from './pages/ProfilePage';
import SubscriptionPage from './pages/SubscriptionPage';
import PaymentMethodsPage from './pages/PaymentMethodsPage';
import PaymentHistoryPage from './pages/PaymentHistoryPage';
import NotificationsPage from './pages/NotificationsPage';
import SettingsPage from './pages/SettingsPage';

// Создаем тему
const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
    background: {
      default: 'transparent',
    },
  },
}, ruRU);

// Компонент для защищенных маршрутов
const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
  const hasCompletedOnboarding = localStorage.getItem('hasCompletedOnboarding') === 'true';
  
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  if (!hasCompletedOnboarding) {
    return <Navigate to="/onboarding" replace />;
  }

  return <>{children}</>;
};

// Компонент для публичных маршрутов
const PublicRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
  
  if (isAuthenticated) {
    return <Navigate to="/" replace />;
  }

  return <>{children}</>;
};

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <GlobalStyles styles={{
        body: {
          background: 'linear-gradient(-45deg, #1e3c72, #2a5298, #6dd5ed, #2193b0)',
          backgroundSize: '400% 400%',
          animation: 'gradientBG 15s ease infinite',
        },
        '@keyframes gradientBG': {
          '0%': { backgroundPosition: '0% 50%' },
          '50%': { backgroundPosition: '100% 50%' },
          '100%': { backgroundPosition: '0% 50%' },
        },
        main: { paddingBottom: theme.spacing(7) },
      }} />
      <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
        <Router>
          <Box sx={{ flexGrow: 1 }}>
            <Routes>
              {/* Public Routes */}
              <Route path="/login" element={
                <PublicRoute>
                  <LoginPage />
                </PublicRoute>
              } />
              <Route path="/register" element={
                <PublicRoute>
                  <RegisterPage />
                </PublicRoute>
              } />
              <Route path="/forgot-password" element={
                <PublicRoute>
                  <ForgotPasswordPage />
                </PublicRoute>
              } />
              <Route path="/verify-email" element={
                <PublicRoute>
                  <EmailVerificationPage />
                </PublicRoute>
              } />
              <Route path="/onboarding" element={
                <PublicRoute>
                  <OnboardingPage />
                </PublicRoute>
              } />

              {/* Protected Routes */}
              <Route path="/" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <HomePage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/workspaces" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <Workspaces />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/bookings" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <div>Бронирования</div>
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <ProfilePage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile/subscription" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <SubscriptionPage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile/payment-methods" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <PaymentMethodsPage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile/payment-history" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <PaymentHistoryPage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile/notifications" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <NotificationsPage />
                  </Container>
                </ProtectedRoute>
              } />
              <Route path="/profile/settings" element={
                <ProtectedRoute>
                  <Navigation />
                  <Container maxWidth="lg" sx={{ mt: 4, position: 'relative', zIndex: 1 }}>
                    <SettingsPage />
                  </Container>
                </ProtectedRoute>
              } />
            </Routes>
          </Box>
        </Router>
        <Footer />
      </Box>
    </ThemeProvider>
  );
}

function Footer() {
  return (
    <Box 
      component="footer"
      sx={{
        py: 2,
        bgcolor: 'rgba(30,60,114,0.95)',
        color: 'white',
        textAlign: 'center',
        boxShadow: '0 -2px 16px 0 rgba(33,147,176,0.2)',
      }}
    >
      <Container maxWidth="lg">
        <Typography variant="body2">
          No php - No problems, 2025
        </Typography>
      </Container>
    </Box>
  );
}

export default App;
