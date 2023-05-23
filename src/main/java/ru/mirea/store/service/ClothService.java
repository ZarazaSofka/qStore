package ru.mirea.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.store.entity.cloth.Cloth;
import ru.mirea.store.entity.cloth.ClothColor;
import ru.mirea.store.entity.cloth.ClothType;
import ru.mirea.store.repository.ClothRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class ClothService {
    private final ClothRepository clothRepository;

    public void add(Cloth cloth) {
        log.info("Adding cloth {}", cloth);
        Optional<Cloth> existing_cloth = findByNameAndTypeAndColorAndSize(cloth);
        if (existing_cloth.isEmpty())
            clothRepository.save(cloth);
    }

    public void changeAmount(Long cloth_id, Long amount) {
        log.info("Increasing cloth amount with id {}", cloth_id);
        Optional<Cloth> existing_cloth = findById(cloth_id);
        if (existing_cloth.isPresent()) {
            existing_cloth.get().changeAmount(amount);
            clothRepository.save(existing_cloth.get());
        }
    }

    public List<Cloth> findAll(Sort sort) {
        return clothRepository.findAll(sort);
    }
    public Optional<Cloth> findById(Long id) {
        return clothRepository.findById(id);
    }

    public Page<Cloth> findByPriceBetween(int min, int max, Pageable pageable) {
        return clothRepository.findByPriceBetweenAndAmountGreaterThan(min, max, pageable, 0);
    }

    private Optional<Cloth> findByNameAndTypeAndColorAndSize(Cloth cloth) {
        return clothRepository.findByNameAndTypeAndColorAndSize(cloth.getName(),
                cloth.getType(), cloth.getColor(), cloth.getSize());
    }
    public Page<Cloth> findByTypeAndColorAndSizeAndPriceBetween(ClothType type, ClothColor color, Integer size,
                                                                int min, int max, Pageable pageable) {
        return clothRepository.findByTypeAndColorAndSizeAndPriceBetweenAndAmountGreaterThan(type, color, size, min, max, pageable, 0);
    }
    public Page<Cloth> findByTypeAndColorAndPriceBetween(ClothType type, ClothColor color, int min, int max,
                                                         Pageable pageable) {
        return clothRepository.findByTypeAndColorAndPriceBetweenAndAmountGreaterThan(type, color, min, max, pageable, 0);
    }
    public Page<Cloth> findByTypeAndSizeAndPriceBetween(ClothType type, Integer size, int min, int max,
                                                        Pageable pageable) {
        return clothRepository.findByTypeAndSizeAndPriceBetweenAndAmountGreaterThan(type, size, min, max, pageable, 0);
    }
    public Page<Cloth> findByColorAndSizeAndPriceBetween(ClothColor color, Integer size, int min, int max,
                                                         Pageable pageable) {
        return clothRepository.findByColorAndSizeAndPriceBetweenAndAmountGreaterThan(color, size, min, max, pageable, 0);
    }
    public Page<Cloth> findByColorAndPriceBetween(ClothColor color, int min, int max, Pageable pageable) {
        return clothRepository.findByColorAndPriceBetweenAndAmountGreaterThan(color, min, max, pageable, 0);
    }
    public Page<Cloth> findByTypeAndPriceBetween(ClothType type, int min, int max, Pageable pageable) {
        return clothRepository.findByTypeAndPriceBetweenAndAmountGreaterThan(type, min, max, pageable, 0);
    }
    public Page<Cloth> findBySizeAndPriceBetween(Integer size, int min, int max, Pageable pageable) {
        return clothRepository.findBySizeAndPriceBetweenAndAmountGreaterThan(size, min, max, pageable, 0);
    }
}