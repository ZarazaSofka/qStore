package ru.mirea.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.store.entity.cloth.Cloth;
import ru.mirea.store.entity.cloth.ClothColor;
import ru.mirea.store.entity.cloth.ClothType;

import java.util.Optional;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
    Optional<Cloth> findByNameAndTypeAndColorAndSize(String name, ClothType type, ClothColor color, Integer size);
    Page<Cloth> findByTypeAndColorAndSizeAndPriceBetweenAndAmountGreaterThan(ClothType type, ClothColor color, Integer size, int min,
                                                         int max, Pageable pageable, Integer amount);
    Page<Cloth> findByTypeAndSizeAndPriceBetweenAndAmountGreaterThan(ClothType type, Integer size, int min, int max, Pageable pageable, Integer amount);
    Page<Cloth> findByColorAndSizeAndPriceBetweenAndAmountGreaterThan(ClothColor color, Integer size, int min, int max, Pageable pageable, Integer amount);
    Page<Cloth> findByTypeAndColorAndPriceBetweenAndAmountGreaterThan(ClothType type, ClothColor color, int min, int max,
                                                  Pageable pageable, Integer amount);
    Page<Cloth> findByTypeAndPriceBetweenAndAmountGreaterThan(ClothType type, int min, int max, Pageable pageable, Integer amount);
    Page<Cloth> findBySizeAndPriceBetweenAndAmountGreaterThan(Integer size, int min, int max, Pageable pageable, Integer amount);
    Page<Cloth> findByColorAndPriceBetweenAndAmountGreaterThan(ClothColor color, int min, int max, Pageable pageable, Integer amount);
    Page<Cloth> findByPriceBetweenAndAmountGreaterThan(int min, int max, Pageable pageable, Integer amount);
}
