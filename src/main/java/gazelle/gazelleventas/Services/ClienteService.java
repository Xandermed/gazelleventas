package gazelle.gazelleventas.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gazelle.gazelleventas.Models.ClienteModel;
import gazelle.gazelleventas.Repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    //Método para guardar usuario
    public void guardarUsuario(ClienteModel cliente) {
        this.clienteRepository.save(cliente);
    }

    //Método para listar todos los usuarios
    public List<ClienteModel> traerTodos() {
        return this.clienteRepository.findAll();
    }

    //Método para buscar por ID
    public Optional<ClienteModel> buscarPorId(String id) {
        return this.clienteRepository.findById(id);
    }

    //Método para buscar por username
    public ClienteModel buscarUsername(String username) {
        return this.clienteRepository.findByUsername(username).orElse(new ClienteModel());
    }

}
