package com.coworking.service.impl;

import com.coworking.model.GuestAccess;
import com.coworking.service.GuestAccessService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class GuestAccessServiceImpl implements GuestAccessService {

    @Override
    public List<GuestAccess> getAllGuestAccess() {
        // TODO: Реализовать получение всех запросов на гостевой доступ
        return List.of();
    }

    @Override
    public GuestAccess getGuestAccessById(UUID guestAccessId) {
        // TODO: Реализовать получение запроса на гостевой доступ по ID
        GuestAccess guestAccess = new GuestAccess();
        guestAccess.setGuestAccessId(guestAccessId);
        return guestAccess;
    }

    @Override
    public GuestAccess createGuestAccess(GuestAccess guestAccess) {
        // TODO: Реализовать создание запроса на гостевой доступ
        guestAccess.setGuestAccessId(UUID.randomUUID());
        return guestAccess;
    }

    @Override
    public GuestAccess updateGuestAccess(UUID guestAccessId, GuestAccess guestAccess) {
        // TODO: Реализовать обновление запроса на гостевой доступ
        guestAccess.setGuestAccessId(guestAccessId);
        return guestAccess;
    }

    @Override
    public void deleteGuestAccess(UUID guestAccessId) {
        // TODO: Реализовать удаление запроса на гостевой доступ
    }
} 