package entity;

public class Usuario {
    private int id_Usuario;
    private int id_Acesso;
    private int id_Matricula;
    private String nome;
    private boolean status;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id_Usuario, int id_Acesso, int id_Matricula, String nome, boolean status, String email, String senha) {
        this.id_Usuario = id_Usuario;
        this.id_Acesso = id_Acesso;
        this.id_Matricula = id_Matricula;
        this.nome = nome;
        this.status = status;
        this.email = email;
        this.senha = senha;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getId_Acesso() {
        return id_Acesso;
    }

    public void setId_Acesso(int id_Acesso) {
        this.id_Acesso = id_Acesso;
    }

    public int getId_Matricula() {
        return id_Matricula;
    }

    public void setId_Matricula(int id_Matricula) {
        this.id_Matricula = id_Matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
