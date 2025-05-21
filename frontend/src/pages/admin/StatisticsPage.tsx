import React, { useState } from 'react';
import {
  Box,
  Paper,
  Typography,
  Grid,
  Card,
  CardContent,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip
} from '@mui/material';
import {
  TrendingUp as TrendingUpIcon,
  People as PeopleIcon,
  AttachMoney as MoneyIcon,
  EventSeat as SeatIcon,
  AccessTime as TimeIcon,
  Star as StarIcon
} from '@mui/icons-material';
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer
} from 'recharts';

interface BookingStats {
  totalBookings: number;
  activeBookings: number;
  completedBookings: number;
  cancelledBookings: number;
  totalRevenue: number;
  averageRating: number;
}

interface WorkspaceStats {
  totalWorkspaces: number;
  availableWorkspaces: number;
  occupiedWorkspaces: number;
  maintenanceWorkspaces: number;
  popularFeatures: { name: string; count: number }[];
}

interface UserStats {
  totalUsers: number;
  activeUsers: number;
  newUsers: number;
  averageBookingsPerUser: number;
}

const StatisticsPage: React.FC = () => {
  const [timeRange, setTimeRange] = useState('month');

  // Имитация данных статистики
  const bookingStats: BookingStats = {
    totalBookings: 156,
    activeBookings: 42,
    completedBookings: 98,
    cancelledBookings: 16,
    totalRevenue: 234000,
    averageRating: 4.8
  };

  const workspaceStats: WorkspaceStats = {
    totalWorkspaces: 24,
    availableWorkspaces: 18,
    occupiedWorkspaces: 4,
    maintenanceWorkspaces: 2,
    popularFeatures: [
      { name: 'Wi-Fi', count: 24 },
      { name: 'Розетки', count: 24 },
      { name: 'Кофе', count: 18 },
      { name: 'Переговорные', count: 12 }
    ]
  };

  const userStats: UserStats = {
    totalUsers: 89,
    activeUsers: 45,
    newUsers: 12,
    averageBookingsPerUser: 3.2
  };

  // Данные для графиков
  const revenueData = [
    { name: 'Янв', revenue: 150000 },
    { name: 'Фев', revenue: 180000 },
    { name: 'Мар', revenue: 210000 },
    { name: 'Апр', revenue: 234000 },
    { name: 'Май', revenue: 250000 },
    { name: 'Июн', revenue: 280000 }
  ];

  const bookingStatusData = [
    { name: 'Активные', value: bookingStats.activeBookings },
    { name: 'Завершенные', value: bookingStats.completedBookings },
    { name: 'Отмененные', value: bookingStats.cancelledBookings }
  ];

  const workspaceStatusData = [
    { name: 'Доступно', value: workspaceStats.availableWorkspaces },
    { name: 'Занято', value: workspaceStats.occupiedWorkspaces },
    { name: 'Обслуживание', value: workspaceStats.maintenanceWorkspaces }
  ];

  const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

  const getTimeRangeLabel = (range: string) => {
    switch (range) {
      case 'week':
        return 'За неделю';
      case 'month':
        return 'За месяц';
      case 'year':
        return 'За год';
      default:
        return 'За месяц';
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h5" component="h1">
          Статистика
        </Typography>
        <FormControl sx={{ minWidth: 200 }}>
          <InputLabel>Период</InputLabel>
          <Select
            value={timeRange}
            label="Период"
            onChange={(e) => setTimeRange(e.target.value)}
          >
            <MenuItem value="week">За неделю</MenuItem>
            <MenuItem value="month">За месяц</MenuItem>
            <MenuItem value="year">За год</MenuItem>
          </Select>
        </FormControl>
      </Box>

      {/* Основные показатели */}
      <Grid container spacing={3} sx={{ mb: 3 }}>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%' }}>
            <CardContent>
              <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                <MoneyIcon sx={{ mr: 1, color: 'primary.main' }} />
                <Typography variant="h6">
                  Доход {getTimeRangeLabel(timeRange)}
                </Typography>
              </Box>
              <Typography variant="h3" color="primary">
                {bookingStats.totalRevenue.toLocaleString()} ₽
              </Typography>
              <Typography variant="body2" color="text.secondary">
                +15% к прошлому периоду
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%' }}>
            <CardContent>
              <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                <PeopleIcon sx={{ mr: 1, color: 'primary.main' }} />
                <Typography variant="h6">
                  Активных пользователей
                </Typography>
              </Box>
              <Typography variant="h3" color="primary">
                {userStats.activeUsers}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                +{userStats.newUsers} новых за период
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%' }}>
            <CardContent>
              <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                <StarIcon sx={{ mr: 1, color: 'primary.main' }} />
                <Typography variant="h6">
                  Средний рейтинг
                </Typography>
              </Box>
              <Typography variant="h3" color="primary">
                {bookingStats.averageRating}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                На основе {bookingStats.completedBookings} отзывов
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* График доходов */}
      <Paper sx={{ p: 3, mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Динамика доходов
        </Typography>
        <Box sx={{ height: 300 }}>
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={revenueData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Line
                type="monotone"
                dataKey="revenue"
                stroke="#2193b0"
                strokeWidth={2}
                name="Доход"
              />
            </LineChart>
          </ResponsiveContainer>
        </Box>
      </Paper>

      {/* Статистика бронирований */}
      <Paper sx={{ p: 3, mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Статистика бронирований
        </Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Box sx={{ height: 300 }}>
              <ResponsiveContainer width="100%" height="100%">
                <PieChart>
                  <Pie
                    data={bookingStatusData}
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                    outerRadius={80}
                    fill="#8884d8"
                    dataKey="value"
                  >
                    {bookingStatusData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <Tooltip />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </Box>
          </Grid>
          <Grid item xs={12} md={6}>
            <Box sx={{ height: 300 }}>
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={workspaceStats.popularFeatures}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="name" />
                  <YAxis />
                  <Tooltip />
                  <Legend />
                  <Bar dataKey="count" fill="#2193b0" name="Количество" />
                </BarChart>
              </ResponsiveContainer>
            </Box>
          </Grid>
        </Grid>
      </Paper>

      {/* Статистика рабочих мест */}
      <Paper sx={{ p: 3, mb: 3 }}>
        <Typography variant="h6" gutterBottom>
          Статистика рабочих мест
        </Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Box sx={{ height: 300 }}>
              <ResponsiveContainer width="100%" height="100%">
                <PieChart>
                  <Pie
                    data={workspaceStatusData}
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                    outerRadius={80}
                    fill="#8884d8"
                    dataKey="value"
                  >
                    {workspaceStatusData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <Tooltip />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </Box>
          </Grid>
          <Grid item xs={12} md={6}>
            <TableContainer>
              <Table size="small">
                <TableHead>
                  <TableRow>
                    <TableCell>Статус</TableCell>
                    <TableCell align="right">Количество</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  <TableRow>
                    <TableCell>Всего</TableCell>
                    <TableCell align="right">{workspaceStats.totalWorkspaces}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>Доступно</TableCell>
                    <TableCell align="right">{workspaceStats.availableWorkspaces}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>Занято</TableCell>
                    <TableCell align="right">{workspaceStats.occupiedWorkspaces}</TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell>На обслуживании</TableCell>
                    <TableCell align="right">{workspaceStats.maintenanceWorkspaces}</TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
        </Grid>
      </Paper>

      {/* Статистика пользователей */}
      <Paper sx={{ p: 3 }}>
        <Typography variant="h6" gutterBottom>
          Статистика пользователей
        </Typography>
        <Grid container spacing={3}>
          <Grid item xs={12} md={4}>
            <Box sx={{ textAlign: 'center' }}>
              <Typography variant="h4" color="primary">
                {userStats.totalUsers}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Всего пользователей
              </Typography>
            </Box>
          </Grid>
          <Grid item xs={12} md={4}>
            <Box sx={{ textAlign: 'center' }}>
              <Typography variant="h4" color="success.main">
                {userStats.activeUsers}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Активных пользователей
              </Typography>
            </Box>
          </Grid>
          <Grid item xs={12} md={4}>
            <Box sx={{ textAlign: 'center' }}>
              <Typography variant="h4" color="info.main">
                {userStats.averageBookingsPerUser}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Среднее количество бронирований
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default StatisticsPage; 