// package com.unit.impulsioneai;
// import com.unit.impulsioneai.controllers.AnunciosController;
// import com.unit.impulsioneai.dtos.AnunciosRecordDto;
// import com.unit.impulsioneai.models.AnunciosModel;
// import com.unit.impulsioneai.repositories.AnunciosRepository;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.BeanUtils;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit4.SpringRunner;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// class AnunciosControllerTest {

//     @Mock
//     private AnunciosRepository anunciosRepository;

//     @InjectMocks
//     private AnunciosController anunciosController;

//     @Before
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//     }

//     @Test
//     public void testSaveAnuncio() {
//         AnunciosRecordDto dto = new AnunciosRecordDto();
//         dto.setTitulo("Título");
//         dto.setDescricao("Descrição");

//         AnunciosModel model = new AnunciosModel();
//         BeanUtils.copyProperties(dto, model);

//         when(anunciosRepository.save(any(AnunciosModel.class))).thenReturn(model);

//         ResponseEntity<AnunciosModel> response = anunciosController.saveAnuncio(dto);

//         assertEquals(HttpStatus.CREATED, response.getStatusCode());
//         assertEquals(model, response.getBody());
//     }

//     @Test
//     public void testGetAllAnuncios() {
//         List<AnunciosModel> anuncios = new ArrayList<>();
//         anuncios.add(new AnunciosModel());
//         anuncios.add(new AnunciosModel());

//         when(anunciosRepository.findAll()).thenReturn(anuncios);

//         ResponseEntity<List<AnunciosModel>> response = anunciosController.getAllAnuncios();

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(anuncios, response.getBody());
//     }

//     @Test
//     public void testGetAnuncio() {
//         UUID id = UUID.randomUUID();
//         AnunciosModel anuncio = new AnunciosModel();

//         when(anunciosRepository.findById(id)).thenReturn(Optional.of(anuncio));

//         ResponseEntity<Object> response = anunciosController.getAnuncios(id);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(anuncio, response.getBody());
//     }

//     @Test
//     public void testUpdateAnuncio() {
//         UUID id = UUID.randomUUID();
//         AnunciosRecordDto dto = new AnunciosRecordDto();
//         dto.setTitulo("Novo Título");
//         dto.setDescricao("Nova Descrição");

//         AnunciosModel existingAnuncio = new AnunciosModel();
//         existingAnuncio.setId(id);

//         when(anunciosRepository.findById(id)).thenReturn(Optional.of(existingAnuncio));

//         ResponseEntity<Object> response = anunciosController.updateAnuncio(id, dto);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertTrue(response.getBody() instanceof AnunciosModel);

//         AnunciosModel updatedAnuncio = (AnunciosModel) response.getBody();
//         assertEquals(dto.getTitulo(), updatedAnuncio.getTitulo());
//         assertEquals(dto.getDescricao(), updatedAnuncio.getDescricao());
//     }

//     @Test
//     public void testDeleteAnuncio() {
//         UUID id = UUID.randomUUID();
//         AnunciosModel anuncio = new AnunciosModel();

//         when(anunciosRepository.findById(id)).thenReturn(Optional.of(anuncio));

//         ResponseEntity<Object> response = anunciosController.deleteAnuncio(id);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals("Anuncio deletado com sucesso", response.getBody());
//     }
// }
