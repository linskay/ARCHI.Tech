import { UserProfile, UpdateProfileData } from '../types/user';
import { api } from './api';
import type { AxiosResponse } from 'axios';

class UserService {
  async getProfile(): Promise<UserProfile> {
    const response: AxiosResponse<UserProfile> = await api.get('/users/profile');
    return response.data;
  }

  async updateProfile(data: UpdateProfileData): Promise<UserProfile> {
    const response: AxiosResponse<UserProfile> = await api.put('/users/profile', data);
    return response.data;
  }

  async updateAvatar(file: File): Promise<UserProfile> {
    const formData = new FormData();
    formData.append('avatar', file);
    
    const response: AxiosResponse<UserProfile> = await api.put('/users/profile/avatar', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  }
}

export const userService = new UserService(); 