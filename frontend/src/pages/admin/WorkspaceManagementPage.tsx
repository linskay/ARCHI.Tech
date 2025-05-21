import React, { useState } from 'react';
import {
  Box,
  Paper,
  Typography,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Chip
} from '@mui/material';
import {
  Edit as EditIcon,
  Delete as DeleteIcon,
  Add as AddIcon,
  CheckCircle as CheckCircleIcon,
  Cancel as CancelIcon
} from '@mui/icons-material';

interface Workspace {
  id: number;
  name: string;
  type: string;
  price: number;
  status: 'available' | 'occupied' | 'maintenance';
  features: string[];
}

const WorkspaceManagementPage: React.FC = () => {
  const [workspaces, setWorkspaces] = useState<Workspace[]>([
    {
      id: 1,
      name: 'Рабочее место "Премиум"',
      type: 'premium',
      price: 1500,
      status: 'available',
      features: ['Wi-Fi', 'Розетки', 'Кофе']
    },
    {
      id: 2,
      name: 'Рабочее место "Стандарт"',
      type: 'standard',
      price: 1000,
      status: 'occupied',
      features: ['Wi-Fi', 'Розетки']
    }
  ]);

  const [openDialog, setOpenDialog] = useState(false);
  const [editingWorkspace, setEditingWorkspace] = useState<Workspace | null>(null);
  const [formData, setFormData] = useState<Partial<Workspace>>({
    name: '',
    type: 'standard',
    price: 0,
    status: 'available',
    features: []
  });

  const handleOpenDialog = (workspace?: Workspace) => {
    if (workspace) {
      setEditingWorkspace(workspace);
      setFormData(workspace);
    } else {
      setEditingWorkspace(null);
      setFormData({
        name: '',
        type: 'standard',
        price: 0,
        status: 'available',
        features: []
      });
    }
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setEditingWorkspace(null);
  };

  const handleSave = () => {
    if (editingWorkspace) {
      setWorkspaces(workspaces.map(w => 
        w.id === editingWorkspace.id ? { ...w, ...formData } : w
      ));
    } else {
      setWorkspaces([...workspaces, { ...formData, id: Date.now() } as Workspace]);
    }
    handleCloseDialog();
  };

  const handleDelete = (id: number) => {
    setWorkspaces(workspaces.filter(w => w.id !== id));
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'available':
        return 'success';
      case 'occupied':
        return 'warning';
      case 'maintenance':
        return 'error';
      default:
        return 'default';
    }
  };

  const getStatusIcon = (status: string) => {
    switch (status) {
      case 'available':
        return <CheckCircleIcon />;
      case 'occupied':
        return <CancelIcon />;
      case 'maintenance':
        return <CancelIcon />;
      default:
        return <CheckCircleIcon />;
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h5" component="h1">
          Управление рабочими местами
        </Typography>
        <Button
          variant="contained"
          startIcon={<AddIcon />}
          onClick={() => handleOpenDialog()}
          sx={{
            background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
            '&:hover': {
              background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
            }
          }}
        >
          Добавить рабочее место
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Название</TableCell>
              <TableCell>Тип</TableCell>
              <TableCell>Стоимость</TableCell>
              <TableCell>Статус</TableCell>
              <TableCell>Особенности</TableCell>
              <TableCell>Действия</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workspaces.map((workspace) => (
              <TableRow key={workspace.id}>
                <TableCell>{workspace.name}</TableCell>
                <TableCell>{workspace.type === 'premium' ? 'Премиум' : 'Стандарт'}</TableCell>
                <TableCell>{workspace.price} ₽/день</TableCell>
                <TableCell>
                  <Chip
                    icon={getStatusIcon(workspace.status)}
                    label={
                      workspace.status === 'available' ? 'Доступно' :
                      workspace.status === 'occupied' ? 'Занято' : 'На обслуживании'
                    }
                    color={getStatusColor(workspace.status) as any}
                    size="small"
                  />
                </TableCell>
                <TableCell>
                  {workspace.features.map((feature, index) => (
                    <Chip
                      key={index}
                      label={feature}
                      size="small"
                      sx={{ mr: 0.5, mb: 0.5 }}
                    />
                  ))}
                </TableCell>
                <TableCell>
                  <IconButton
                    size="small"
                    onClick={() => handleOpenDialog(workspace)}
                    sx={{ mr: 1 }}
                  >
                    <EditIcon />
                  </IconButton>
                  <IconButton
                    size="small"
                    onClick={() => handleDelete(workspace.id)}
                    color="error"
                  >
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Dialog open={openDialog} onClose={handleCloseDialog} maxWidth="sm" fullWidth>
        <DialogTitle>
          {editingWorkspace ? 'Редактировать рабочее место' : 'Добавить рабочее место'}
        </DialogTitle>
        <DialogContent>
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, pt: 2 }}>
            <TextField
              label="Название"
              fullWidth
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            />
            <FormControl fullWidth>
              <InputLabel>Тип</InputLabel>
              <Select
                value={formData.type}
                label="Тип"
                onChange={(e) => setFormData({ ...formData, type: e.target.value })}
              >
                <MenuItem value="standard">Стандарт</MenuItem>
                <MenuItem value="premium">Премиум</MenuItem>
              </Select>
            </FormControl>
            <TextField
              label="Стоимость"
              type="number"
              fullWidth
              value={formData.price}
              onChange={(e) => setFormData({ ...formData, price: Number(e.target.value) })}
            />
            <FormControl fullWidth>
              <InputLabel>Статус</InputLabel>
              <Select
                value={formData.status}
                label="Статус"
                onChange={(e) => setFormData({ ...formData, status: e.target.value as any })}
              >
                <MenuItem value="available">Доступно</MenuItem>
                <MenuItem value="occupied">Занято</MenuItem>
                <MenuItem value="maintenance">На обслуживании</MenuItem>
              </Select>
            </FormControl>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog}>Отмена</Button>
          <Button
            onClick={handleSave}
            variant="contained"
            sx={{
              background: 'linear-gradient(90deg, #2193b0 0%, #6dd5ed 100%)',
              '&:hover': {
                background: 'linear-gradient(90deg, #6dd5ed 0%, #2193b0 100%)',
              }
            }}
          >
            Сохранить
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default WorkspaceManagementPage; 