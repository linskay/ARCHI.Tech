import React from 'react';
import { Box, Card, CardContent, Typography, CardActions, Button } from '@mui/material';

interface Workspace {
  id: string;
  name: string;
  description: string;
  pricePerHour: number;
  status: 'AVAILABLE' | 'OCCUPIED' | 'UNDER_MAINTENANCE';
}

const cardStyle = {
  transition: 'transform 0.35s cubic-bezier(.4,2,.6,1), box-shadow 0.35s cubic-bezier(.4,2,.6,1)',
  background: 'linear-gradient(135deg, #232526 0%, #414345 100%)',
  boxShadow: '0 2px 16px 0 rgba(33,147,176,0.10)',
  '&:hover': {
    transform: 'scale(1.06) rotate(-1deg)',
    background: 'linear-gradient(135deg, #2193b0 0%, #6dd5ed 100%)',
    boxShadow: '0 8px 32px 0 rgba(33,147,176,0.25)',
  },
};

const Workspaces: React.FC = () => {
  // Временные данные для примера
  const workspaces: Workspace[] = [
    {
      id: '1',
      name: 'Рабочее место №1',
      description: 'Удобное рабочее место с компьютером',
      pricePerHour: 200,
      status: 'AVAILABLE'
    },
    {
      id: '2',
      name: 'Конференц-зал',
      description: 'Просторный зал для встреч',
      pricePerHour: 500,
      status: 'AVAILABLE'
    }
  ];

  return (
    <Box sx={{ display: 'grid', gridTemplateColumns: { xs: '1fr', sm: '1fr 1fr', md: '1fr 1fr 1fr' }, gap: 3 }}>
      {workspaces.map((workspace) => (
        <Box key={workspace.id}>
          <Card sx={cardStyle}>
            <CardContent>
              <Typography variant="h5" component="div">
                {workspace.name}
              </Typography>
              <Typography color="text.secondary" sx={{ mb: 1.5 }}>
                {workspace.description}
              </Typography>
              <Typography variant="body2">
                Цена: {workspace.pricePerHour} ₽/час
              </Typography>
              <Typography variant="body2" color={workspace.status === 'AVAILABLE' ? 'success.main' : 'error.main'}>
                Статус: {workspace.status === 'AVAILABLE' ? 'Доступно' : 'Занято'}
              </Typography>
            </CardContent>
            <CardActions>
              <Button size="small" color="primary">
                Забронировать
              </Button>
              <Button size="small" color="primary">
                Подробнее
              </Button>
            </CardActions>
          </Card>
        </Box>
      ))}
    </Box>
  );
};

export default Workspaces; 