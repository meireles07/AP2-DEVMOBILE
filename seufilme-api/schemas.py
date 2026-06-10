from pydantic import BaseModel
from datetime import datetime

# schemas.py fala com o app Android

class RespostaCreate(BaseModel):
    nome: str
    genero_favorito: str
    plataforma: str
    humor: str
    tipo_conteudo: str

# Schema usado quando a API RETORNA dados para o Android (GET)
class RespostaResponse(BaseModel):
    id: int
    nome: str
    genero_favorito: str
    plataforma: str
    humor: str
    tipo_conteudo: str
    data_criacao: datetime

    class Config:
        from_attributes = True