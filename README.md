<h1 align="center"> Testando a lib Bucket4j </h1>

## :floppy_disk: Banco de dados

![Dados no H2](https://user-images.githubusercontent.com/12420676/178389167-3ebba768-b2f9-4183-935f-aa2d58b78a87.png)

- `URL`: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- `JDBC URL`: jdbc:h2:mem:dogs
- `USER NAME`: dogs

## :clipboard: Logins de Teste

![Logins para teste](https://user-images.githubusercontent.com/12420676/178389710-c76bbf81-b687-4502-a128-a35d820ba047.png)

- `Yago`: dog@awsman.com.br
- `Victor`: dog@santos.com.br
- `Matheus`: dog@mg.com.br

![image](https://user-images.githubusercontent.com/12420676/178391377-e870cdd9-6b1b-42ed-a869-2d8edcee5a39.png)

- `FREE`: 2 Request por minuto
- `BASIC`: 3 Request por minuto
- `PROFESSIONAL`: 4 Request por minuto

##### :warning: O usuário Yago está sem regras para acessar a aplicação.

### Testando a rota GET
```bash
curl --location --request POST 'localhost:8080/bordoes-pedrim' --header 'login: dog@santos.com.br'
```

### Testando a rota POST
```bash
curl --location --request GET 'localhost:8080/bordoes-pedrim' --header 'login: dog@santos.com.br'
```

## Autor

| [<img src="https://avatars.githubusercontent.com/u/12420676?s=400&u=e4ec0232892c690d12dbd9a3c3d07e549290baa4&v=4" width=115><br><sub>Matheus Melo</sub>](https://github.com/matheus306)
| :---:

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
