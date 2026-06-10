from sqlalchemy import Column, Integer, String, DateTime
from datetime import datetime
from database import Base

# Essa classe representa a tabela "respostas" no banco de dados
# Cada atributo com Column é uma coluna da tabela
class Resposta(Base):
    __tablename__ = "respostas"

    # Chave primária
    id = Column(Integer, primary_key=True, index=True)

    # Nome do usuário que preencheu o formulário
    nome = Column(String, nullable=False)

    # Gênero favorito (Ação, Comédia, Drama, etc.)
    genero_favorito = Column(String, nullable=False)

    # Plataforma preferida (Netflix, Prime, etc.)
    plataforma = Column(String, nullable=False)

    # Humor atual do usuário (Animado, Relaxado, Triste, etc.)
    humor = Column(String, nullable=False)

    # Tipo de conteúdo preferido (Filme ou Série)
    tipo_conteudo = Column(String, nullable=False)

    # Data e hora em que o formulário foi preenchido (automaticamente)
    data_criacao = Column(DateTime, default=datetime.now)