package com.prueba.tecnica.pruebantt;


import com.prueba.tecnica.pruebantt.dto.AssetDTO;
import com.prueba.tecnica.pruebantt.models.Depreciation;
import com.prueba.tecnica.pruebantt.repository.DepreciationRepository;
import com.prueba.tecnica.pruebantt.service.AssetService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PruebanttApplicationTests {

	@Autowired
	DepreciationRepository depreciationRepository;
	@Autowired
	AssetService assetService;

	@Test
	void test_find_asset(){
		AssetDTO assetDTO = assetService.consulta(1);
		assertNotNull(assetDTO);
		assertEquals("Nombre",assetDTO.getName());
		assertEquals("Descripcion",assetDTO.getDescription());
		assertEquals("121313", assetDTO.getSerialNumber());
		assertEquals(1760, assetDTO.getValueWithDepreciation());
	}

	@Test
	void test_find_all_depreciation() {
		List<Depreciation> depreciations = depreciationRepository.findAll();
		assertFalse(depreciations.isEmpty());
		assertEquals(1, depreciations.size());
	}

	@Test
	void test_find_by_id_depreciation() {
		Optional<Depreciation> depreciation = depreciationRepository.findById(1);
		assertTrue(depreciation.isPresent());
		assertEquals("2023", depreciation.orElseThrow().getFiscalYear());
		assertEquals("4.00", depreciation.orElseThrow().getPercentage().toString());
	}

	@Test
	void test_create_depreciation() {
		Depreciation depreciation = new Depreciation();
		depreciation.setPercentage(new BigDecimal("6.00"));
		depreciation.setFiscalYear("2024");
		Depreciation newField = depreciationRepository.save(depreciation);
		assertNotNull(newField);
		assertTrue(newField.getId() > 0);
		assertEquals("2024", newField.getFiscalYear());
		assertEquals("6.00", newField.getPercentage().toString());
	}

	@Test
	void test_update_depreciation() {
		Optional<Depreciation> depreciation = depreciationRepository.findById(1);
		Depreciation depreciation_ = depreciation.get();
		assertNotNull(depreciation_);
		depreciation_.setPercentage(new BigDecimal("6.33"));
		Depreciation depreciation1 = depreciationRepository.save(depreciation_);
		assertEquals("2023", depreciation1.getFiscalYear());
		assertEquals("6.33", depreciation1.getPercentage().toString());
		depreciation1.setPercentage(new BigDecimal("4.00"));
		Depreciation depreciation2 = depreciationRepository.save(depreciation1);
		assertEquals("4.00", depreciation1.getPercentage().toString());
	}

	@Test
	void test_delete_depreciation(){
		Depreciation depreciation = depreciationRepository.findById(2).orElseThrow();
		assertNotNull(depreciation);
		depreciationRepository.delete(depreciation);
		assertThrows(NoSuchElementException.class, ()->{
			depreciationRepository.findById(2).orElseThrow();
		});
		assertEquals(1,depreciationRepository.findAll().size());
	}
}
