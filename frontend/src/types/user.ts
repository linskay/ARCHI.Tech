export interface User {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  avatar?: string;
  phone?: string;
  role: 'user' | 'admin';
  createdAt: string;
  updatedAt: string;
}

export interface UserProfile {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  phone?: string;
  avatar?: string;
  notifications: {
    email: boolean;
    push: boolean;
    sms: boolean;
  };
}

export interface PaymentMethod {
  id: string;
  type: 'card';
  last4: string;
  brand: string;
  isDefault: boolean;
  expiryMonth: number;
  expiryYear: number;
}

export interface UpdateProfileData {
  firstName: string;
  lastName: string;
  email: string;
  phone?: string;
  notifications: {
    email: boolean;
    push: boolean;
    sms: boolean;
  };
}

export interface UpdatePasswordData {
  currentPassword: string;
  newPassword: string;
  confirmPassword: string;
} 