from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

# Banco de dados Sqlite
SQLALCHEMY_DATABASE_URL = "sqlite:///./seufilme.db"

# O engine é a "conexão" com o banco de dados
engine = create_engine(
    SQLALCHEMY_DATABASE_URL,
    connect_args={"check_same_thread": False}
)

# SessionLocal é o que usamos para abrir e fechar sessões com o banco
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# (tabelas das respostas)
Base = declarative_base()

# Função que abre uma sessão com o banco e fecha automaticamente ao terminar (Endpoint)
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()