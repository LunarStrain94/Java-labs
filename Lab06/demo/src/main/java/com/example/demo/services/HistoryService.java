package com.example.demo.services;

import com.example.demo.config.HistoryEnter;
import com.example.demo.models.Device;
import com.example.demo.models.History;
import com.example.demo.repositories.DeviceRepository;
import com.example.demo.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    HistoryService(HistoryRepository historyRepository, DeviceRepository deviceRepository) {
        this.historyRepository = historyRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<History> getAll() {return historyRepository.findAll();}

    public History getBy(String id) {
        int id1 = Integer.valueOf(id);
        return historyRepository.findById(id1);
    }

    public void addNewHistory(HistoryEnter historyEnter) {
        if (historyEnter.getMonth() > 12 || historyEnter.getMonth() < 1) {
            throw new IllegalStateException("Illegal month");
        }
        if (historyEnter.getYear() > LocalDate.now().getYear() || historyEnter.getYear() == LocalDate.now().getYear() && historyEnter.getMonth() > LocalDate.now().getMonthValue()) {
            throw new IllegalStateException("Illegal year");
        }
        if (!deviceRepository.existsById(historyEnter.getDeviceId())) {
            throw new IllegalStateException("Device doesn't exist");
        }
        Device device = deviceRepository.findById(historyEnter.getDeviceId());
        List<History> histories = device.getHistory();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getMonthT() == historyEnter.getMonth() && histories.get(i).getYearT() == historyEnter.getYear()) {
                throw new IllegalStateException("Month taken");
            }
        }

        var history = new History();
        history.setMonthT(historyEnter.getMonth());
        history.setYearT(historyEnter.getYear());
        history.setFullConsumption(historyEnter.getFullCons());
        history.setAverageConsumption(historyEnter.getAvgCons());
        historyRepository.save(history);
        history.setDevice(device);
        histories.add(history);
        device.setHistory(histories);
        deviceRepository.save(device);
        historyRepository.save(history);
    }

    public void deleteById(String id) {
        int id1 = Integer.valueOf(id);
        boolean historyExists = historyRepository.existsById(id1);
        if (!historyExists) {
            throw new IllegalStateException("History log doesn't exist");
        }
        var device = historyRepository.getById(id1).getDevice();
        device.getHistory().remove(historyRepository.getById(id1));
        historyRepository.deleteById(id1);
    }

    public List<History> getLogByYear(int year) {
        List<History> histories = historyRepository.findAll();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getYearT() != year) {
                histories.remove(i);
                i--;
            }
        }
        return histories;
    }

    public String getAllByYear(int year) {
        List<History> histories = historyRepository.findAll();
        int fullConsumption = 0;
        int averageConsumption = 0;
        int num = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getYearT() == year) {
                fullConsumption += histories.get(i).getFullConsumption();
                num += 1;
            }
        }
        averageConsumption = fullConsumption / num;
        return "{\n    \"fullConsumption\": \"" + fullConsumption + "\"\n    \"averageConsumption\": \"" + averageConsumption + "\"\n}";
    }

    public History getByMonth(int year, int month) {
        List<History> histories = historyRepository.findAll();
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i).getYearT() == year && histories.get(i).getMonthT() == month) {
                return histories.get(i);
            }
        }
        return null;
    }

    public void updateHistory(HistoryEnter historyEnter, int id) {
        History history = historyRepository.findById(id);
        history.setYearT(historyEnter.getYear());
        history.setMonthT(historyEnter.getMonth());
        if (deviceRepository.existsById(historyEnter.getDeviceId())) {
            history.setDevice(deviceRepository.findById(historyEnter.getDeviceId()));
        }
        historyRepository.save(history);
    }
}
