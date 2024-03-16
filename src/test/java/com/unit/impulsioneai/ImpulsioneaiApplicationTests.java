import com.unit.impulsioneai.controllers.UsuarioController;
import com.unit.impulsioneai.dtos.UsuarioRecordDto;
import com.unit.impulsioneai.models.UsuarioModel;
import com.unit.impulsioneai.repositories.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImpulsioneaiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUsuario() {
        UsuarioRecordDto dto = new UsuarioRecordDto();
        dto.setNome("Nome");
        dto.setEmail("email@example.com");

        UsuarioModel model = new UsuarioModel();
        BeanUtils.copyProperties(dto, model);

        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(model);

        ResponseEntity<UsuarioModel> response = usuarioController.saveUsuario(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(model, response.getBody());
    }

    @Test
    public void testGetAllUsuarios() {
        List<UsuarioModel> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioModel());
        usuarios.add(new UsuarioModel());

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        ResponseEntity<List<UsuarioModel>> response = usuarioController.getAllUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    public void testGetUsuario() {
        UUID id = UUID.randomUUID();
        UsuarioModel usuario = new UsuarioModel();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        ResponseEntity<Object> response = usuarioController.getUsuario(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testUpdateUsuario() {
        UUID id = UUID.randomUUID();
        UsuarioRecordDto dto = new UsuarioRecordDto();
        dto.setNome("Novo Nome");
        dto.setEmail("novoemail@example.com");

        UsuarioModel existingUsuario = new UsuarioModel();
        existingUsuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existingUsuario));

        ResponseEntity<Object> response = usuarioController.updateUsuario(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UsuarioModel);

        UsuarioModel updatedUsuario = (UsuarioModel) response.getBody();
        assertEquals(dto.getNome(), updatedUsuario.getNome());
        assertEquals(dto.getEmail(), updatedUsuario.getEmail());
    }

    @Test
    public void testDeleteUsuario() {
        UUID id = UUID.randomUUID();
        UsuarioModel usuario = new UsuarioModel();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        ResponseEntity<Object> response = usuarioController.deleteUsuario(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário deletado com sucesso", response.getBody());
    }
}
