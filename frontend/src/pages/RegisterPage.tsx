import React, { useState } from 'react';
import {
  Box,
  Container,
  TextField,
  Button,
  Typography,
  Paper,
  Grid,
  Card,
  CardContent,
  CardActions,
  Radio,
  RadioGroup,
  FormControlLabel,
  FormControl,
  FormLabel,
  useTheme,
  useMediaQuery,
} from '@mui/material';
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';

interface Plan {
  id: string;
  name: string;
  price: number;
  features: string[];
  recommended?: boolean;
}

const plans: Plan[] = [
  {
    id: 'basic',
    name: 'Basic',
    price: 0,
    features: [
      'Access to basic workspace',
      'Standard support',
      'Basic amenities',
    ],
  },
  {
    id: 'pro',
    name: 'Professional',
    price: 29,
    features: [
      'Priority workspace access',
      '24/7 support',
      'Premium amenities',
      'Meeting room access',
      'Parking space',
    ],
    recommended: true,
  },
  {
    id: 'enterprise',
    name: 'Enterprise',
    price: 99,
    features: [
      'Dedicated workspace',
      'VIP support',
      'All amenities included',
      'Unlimited meeting rooms',
      'Reserved parking',
      'Private office options',
    ],
  },
];

const RegisterPage: React.FC = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    name: '',
    plan: 'basic',
  });
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Implement registration logic
  };

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        staggerChildren: 0.1,
      },
    },
  };

  const itemVariants = {
    hidden: { y: 20, opacity: 0 },
    visible: {
      y: 0,
      opacity: 1,
    },
  };

  return (
    <Box
      sx={{
        minHeight: '100vh',
        display: 'flex',
        alignItems: 'center',
        background: 'linear-gradient(135deg, #1a237e 0%, #0d47a1 100%)',
        py: 4,
      }}
    >
      <Container maxWidth="lg">
        <motion.div
          initial="hidden"
          animate="visible"
          variants={containerVariants}
        >
          <Paper
            elevation={24}
            sx={{
              p: 4,
              borderRadius: 2,
              background: 'rgba(255, 255, 255, 0.95)',
              backdropFilter: 'blur(10px)',
            }}
          >
            <motion.div variants={itemVariants}>
              <Typography
                variant="h4"
                component="h1"
                align="center"
                gutterBottom
                sx={{
                  fontWeight: 700,
                  background: 'linear-gradient(45deg, #1a237e, #0d47a1)',
                  backgroundClip: 'text',
                  textFillColor: 'transparent',
                  mb: 4,
                }}
              >
                Create Your Account
              </Typography>
            </motion.div>

            <form onSubmit={handleSubmit}>
              <Grid container spacing={3}>
                <Grid item xs={12} md={6}>
                  <motion.div variants={itemVariants}>
                    <TextField
                      fullWidth
                      label="Full Name"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                      sx={{ mb: 2 }}
                      InputProps={{
                        sx: { borderRadius: 2 },
                      }}
                    />
                    <TextField
                      fullWidth
                      label="Email"
                      name="email"
                      type="email"
                      value={formData.email}
                      onChange={handleChange}
                      sx={{ mb: 2 }}
                      InputProps={{
                        sx: { borderRadius: 2 },
                      }}
                    />
                    <TextField
                      fullWidth
                      label="Password"
                      name="password"
                      type="password"
                      value={formData.password}
                      onChange={handleChange}
                      sx={{ mb: 2 }}
                      InputProps={{
                        sx: { borderRadius: 2 },
                      }}
                    />
                    <TextField
                      fullWidth
                      label="Confirm Password"
                      name="confirmPassword"
                      type="password"
                      value={formData.confirmPassword}
                      onChange={handleChange}
                      sx={{ mb: 2 }}
                      InputProps={{
                        sx: { borderRadius: 2 },
                      }}
                    />
                  </motion.div>
                </Grid>

                <Grid item xs={12} md={6}>
                  <motion.div variants={itemVariants}>
                    <FormControl component="fieldset" sx={{ width: '100%' }}>
                      <FormLabel component="legend">Select Your Plan</FormLabel>
                      <RadioGroup
                        name="plan"
                        value={formData.plan}
                        onChange={handleChange}
                      >
                        <Grid container spacing={2}>
                          {plans.map((plan) => (
                            <Grid item xs={12} key={plan.id}>
                              <Card
                                sx={{
                                  border: plan.recommended
                                    ? `2px solid ${theme.palette.primary.main}`
                                    : '2px solid transparent',
                                  position: 'relative',
                                  transition: 'transform 0.2s',
                                  '&:hover': {
                                    transform: 'translateY(-4px)',
                                  },
                                }}
                              >
                                {plan.recommended && (
                                  <Box
                                    sx={{
                                      position: 'absolute',
                                      top: -12,
                                      right: 16,
                                      backgroundColor: 'primary.main',
                                      color: 'white',
                                      px: 1,
                                      py: 0.5,
                                      borderRadius: 1,
                                      fontSize: '0.75rem',
                                    }}
                                  >
                                    Recommended
                                  </Box>
                                )}
                                <CardContent>
                                  <FormControlLabel
                                    value={plan.id}
                                    control={<Radio />}
                                    label={
                                      <Box>
                                        <Typography variant="h6">
                                          {plan.name}
                                        </Typography>
                                        <Typography
                                          variant="h4"
                                          color="primary"
                                          sx={{ my: 1 }}
                                        >
                                          ${plan.price}
                                          <Typography
                                            component="span"
                                            variant="subtitle1"
                                            color="text.secondary"
                                          >
                                            /month
                                          </Typography>
                                        </Typography>
                                        <Box component="ul" sx={{ pl: 2 }}>
                                          {plan.features.map((feature) => (
                                            <Typography
                                              component="li"
                                              key={feature}
                                              variant="body2"
                                              sx={{ mb: 0.5 }}
                                            >
                                              {feature}
                                            </Typography>
                                          ))}
                                        </Box>
                                      </Box>
                                    }
                                    sx={{ width: '100%', m: 0 }}
                                  />
                                </CardContent>
                              </Card>
                            </Grid>
                          ))}
                        </Grid>
                      </RadioGroup>
                    </FormControl>
                  </motion.div>
                </Grid>
              </Grid>

              <motion.div variants={itemVariants}>
                <Button
                  fullWidth
                  variant="contained"
                  size="large"
                  type="submit"
                  sx={{
                    mt: 4,
                    borderRadius: 2,
                    py: 1.5,
                    background: 'linear-gradient(45deg, #1a237e, #0d47a1)',
                    '&:hover': {
                      background: 'linear-gradient(45deg, #0d47a1, #1a237e)',
                    },
                  }}
                >
                  Create Account
                </Button>
              </motion.div>

              <Box sx={{ textAlign: 'center', mt: 3 }}>
                <Typography variant="body2" color="text.secondary">
                  Already have an account?{' '}
                  <Link
                    to="/login"
                    style={{
                      color: theme.palette.primary.main,
                      textDecoration: 'none',
                      fontWeight: 600,
                    }}
                  >
                    Sign In
                  </Link>
                </Typography>
              </Box>
            </form>
          </Paper>
        </motion.div>
      </Container>
    </Box>
  );
};

export default RegisterPage; 