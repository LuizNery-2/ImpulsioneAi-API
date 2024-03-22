import com.unit.impulsioneai.controllers.PlanoAssinaturaController;
import com.unit.impulsioneai.models.PlanoAssinaturaModel;
import com.unit.impulsioneai.repositories.PlanoAssinaturaRepository;
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

    @Mock
    private PlanoAssinaturaRepository planoAssinaturaRepository;

    @InjectMocks
    private PlanoAssinaturaController planoAssinaturaController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveAssinatura() {
        PlanoAssinaturaModel model = new PlanoAssinaturaModel();
        model.setNome("Plano 1");

        when(planoAssinaturaRepository.save(any(PlanoAssinaturaModel.class))).thenReturn(model);

        ResponseEntity<PlanoAssinaturaModel> response = planoAssinaturaController.saveAssinatura(model);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(model, response.getBody());
    }

    @Test
    public void testGetAllAssinaturas() {
        List<PlanoAssinaturaModel> assinaturas = new ArrayList<>();
        assinaturas.add(new PlanoAssinaturaModel());
        assinaturas.add(new PlanoAssinaturaModel());

        when(planoAssinaturaRepository.findAll()).thenReturn(assinaturas);

        ResponseEntity<List<PlanoAssinaturaModel>> response = planoAssinaturaController.getAllAssinaturas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assinaturas, response.getBody());
    }

    @Test
    public void testGetAssinaturas() {
        UUID id = UUID.randomUUID();
        PlanoAssinaturaModel assinatura = new PlanoAssinaturaModel();

        when(planoAssinaturaRepository.findById(id)).thenReturn(Optional.of(assinatura));

        ResponseEntity<Object> response = planoAssinaturaController.getAssinaturas(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assinatura, response.getBody());
    }

    @Test
    public void testUpdateAssinatura() {
        UUID id = UUID.randomUUID();
        PlanoAssinaturaModel model = new PlanoAssinaturaModel();
        model.setNome("Novo Plano");

        PlanoAssinaturaModel existingAssinatura = new PlanoAssinaturaModel();
        existingAssinatura.setId(id);

        when(planoAssinaturaRepository.findById(id)).thenReturn(Optional.of(existingAssinatura));

        ResponseEntity<Object> response = planoAssinaturaController.updateAssinatura(id, model);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof PlanoAssinaturaModel);

        PlanoAssinaturaModel updatedAssinatura = (PlanoAssinaturaModel) response.getBody();
        assertEquals(model.getNome(), updatedAssinatura.getNome());
    }

    @Test
    public void testDeleteAssinatura() {
        UUID id = UUID.randomUUID();
        PlanoAssinaturaModel assinatura = new PlanoAssinaturaModel();

        when(planoAssinaturaRepository.findById(id)).thenReturn(Optional.of(assinatura));

        ResponseEntity<Object> response = planoAssinaturaController.deleteAssinatura(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Assinatura deletada com sucesso", response.getBody());
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

    @Test
    public void testSaveEmpreendedor() {
        EmpreendedoresRecordDto dto = new EmpreendedoresRecordDto();
        dto.setNome("Nome");
        dto.setEmail("email@example.com");

        EmpreendedorModel model = new EmpreendedorModel();
        BeanUtils.copyProperties(dto, model);

        when(empreendedoresRepository.save(any(EmpreendedorModel.class))).thenReturn(model);

        ResponseEntity<EmpreendedorModel> response = empreendedoresController.saveEmpreendedor(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(model, response.getBody());
    }

    @Test
    public void testGetAllEmpreendedores() {
        List<EmpreendedorModel> empreendedores = new ArrayList<>();
        empreendedores.add(new EmpreendedorModel());
        empreendedores.add(new EmpreendedorModel());

        when(empreendedoresRepository.findAll()).thenReturn(empreendedores);

        ResponseEntity<List<EmpreendedorModel>> response = empreendedoresController.getAllEmpreendedores();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empreendedores, response.getBody());
    }

    @Test
    public void testGetEmpreendedor() {
        UUID id = UUID.randomUUID();
        EmpreendedorModel empreendedor = new EmpreendedorModel();

        when(empreendedoresRepository.findById(id)).thenReturn(Optional.of(empreendedor));

        ResponseEntity<Object> response = empreendedoresController.getEmpreendedor(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(empreendedor, response.getBody());
    }

    @Test
    public void testUpdateEmpreendedor() {
        UUID id = UUID.randomUUID();
        EmpreendedoresRecordDto dto = new EmpreendedoresRecordDto();
        dto.setNome("Novo Nome");
        dto.setEmail("novoemail@example.com");

        EmpreendedorModel existingEmpreendedor = new EmpreendedorModel();
        existingEmpreendedor.setId(id);

        when(empreendedoresRepository.findById(id)).thenReturn(Optional.of(existingEmpreendedor));

        ResponseEntity<Object> response = empreendedoresController.updateEmpreendedor(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof EmpreendedorModel);

        EmpreendedorModel updatedEmpreendedor = (EmpreendedorModel) response.getBody();
        assertEquals(dto.getNome(), updatedEmpreendedor.getNome());
        assertEquals(dto.getEmail(), updatedEmpreendedor.getEmail());
    }

    @Test
    public void testDeleteEmpreendedor() {
        UUID id = UUID.randomUUID();
        EmpreendedorModel empreendedor = new EmpreendedorModel();

        when(empreendedoresRepository.findById(id)).thenReturn(Optional.of(empreendedor));

        ResponseEntity<Object> response = empreendedoresController.deleteUsuario(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Empreendedor deletado com sucesso", response.getBody());
    }
}
