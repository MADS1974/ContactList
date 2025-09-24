# 📱 ContactList App

Um aplicativo Android de lista de contatos desenvolvido em Kotlin.
O objetivo principal é demonstrar a implementação de operações CRUD (Criar, Ler, Atualizar, Excluir) em uma lista de objetos, utilizando componentes clássicos do Android View System.

Este projeto foi criado para fins acadêmicos no contexto da disciplina de Desenvolvimento de Aplicações Móveis, com foco em:

Navegação entre Activities

Gerenciamento de estado

Interação do usuário com listas de dados

## 🚀 Funcionalidades

Listagem de Contatos: Exibe uma lista de contatos em uma ListView com layout de item personalizado.

Adicionar Novo Contato: Abre a ContactActivity para preenchimento dos dados de um novo contato, retornando o resultado para a tela principal via ActivityResultLauncher.

Visualizar Detalhes: Ao clicar em um item da lista, exibe os detalhes do contato em modo de leitura.

Editar Contato: Através de um menu de contexto (pressionar e segurar), abre a tela de edição com os dados já preenchidos.

Remover Contato: Também pelo menu de contexto, permite excluir contatos.

Uso de Intents e Extras: Comunicação entre MainActivity e ContactActivity via Intents, passando objetos Contact (Parcelable) como extras.

## 🛠 Tecnologias Utilizadas

Kotlin — Linguagem principal

Android Studio — IDE de desenvolvimento

ViewBinding — Acesso seguro e tipado aos elementos de UI

ActivityResultLauncher — Navegação e retorno entre Activities (práticas modernas do Android)

ListView com ArrayAdapter personalizado — Exibição eficiente da lista de contatos

Menus (Options e Context) — Fornecem ações como adicionar, editar e remover

## 📂 Estrutura do Projeto

```
KotlinContactList/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/edu/scl/ifsp/mads/contactlist/
│   │   │   │   ├── view/
│   │   │   │   │   ├── MainActivity.kt       # Activity principal (lista de contatos)
│   │   │   │   │   └── ContactActivity.kt    # Activity para adicionar/editar/ver
│   │   │   │   ├── adapter/
│   │   │   │   │   └── ContactAdapter.kt     # Adaptador da ListView
│   │   │   │   └── model/
│   │   │   │       ├── Contact.kt            # Data class (Parcelable)
│   │   │   │       └── Constant.kt           # Constantes para Intents
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_contact.xml
│   │   │   │   │   └── tile_contact.xml      # Layout de item da lista
│   │   │   │   ├── menu/
│   │   │   │   │   ├── menu_main.xml         # Menu de opções (Adicionar)
│   │   │   │   │   └── context_menu_main.xml # Menu de contexto (Editar/Remover)
│   │   │   │   └── values/ (strings, estilos, cores etc.)
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle (nível do módulo)
│
├── build.gradle (nível do projeto)
├── settings.gradle
└── gradle/

   ```
## 📸 Demonstração do App

### 01 - ContactList
Visualização via Desktop.  
![Project preview on desktop](screens/1%20ContactList.png)

### 02 - ContactList
Editando Contato – Formulário para inserir novo contato.  
![Contact Edit+Add](screens/2%20ContactList.png)

### 03 - ContactList
Detalhes Contato – Opções Editar e Remover.  
![Contact Details](screens/3%20ContactList.png)

### 04 - ContactList
Remover Contato – Exibe detalhes de remoção.  
![Remove Contact](screens/4%20ContactList.png)


## ⚠️ Observações

Os dados são armazenados apenas em memória. Ao fechar o app, alterações serão perdidas.

A MainActivity utiliza o método fillContacts() para popular a lista com dados de exemplo ao iniciar.

## ▶️ Como Executar o Projeto

Clone este repositório:

git clone https://github.com/MADS1974/ContactList.git


Abra o projeto no Android Studio

Aguarde a sincronização do Gradle

Execute em um emulador ou dispositivo físico

## 🎥 Vídeo Demonstrativo


👉 [Link para o vídeo xContactListVideo.mp4]


## 📚 Créditos

Projeto acadêmico desenvolvido para a disciplina **Desenvolvimento para Android 1 – D1DA1**, ministrada pelo professor **Pedro Northon Nobile (IFSP)**. 


## 🙋‍♂️

🔗 Conecte-se comigo

[LinkedIn - Márcio Adriano](https://www.linkedin.com/in/mads1974/)
