from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session
from database import engine, get_db, Base
import models
import schemas

# Cria todas as tabelas no banco de dados
Base.metadata.create_all(bind=engine)

# FastApi
app = FastAPI(
    title="SeuFilme API",
    description="API de recomendação de filmes e séries",
    version="1.0.0"
)

# O Android envia as respostas do formulário
@app.post("/respostas", response_model=schemas.RespostaResponse)
def criar_resposta(resposta: schemas.RespostaCreate, db: Session = Depends(get_db)):
    # Cria um novo objeto do modelo com os dados recebidos
    nova_resposta = models.Resposta(
        nome=resposta.nome,
        genero_favorito=resposta.genero_favorito,
        plataforma=resposta.plataforma,
        humor=resposta.humor,
        tipo_conteudo=resposta.tipo_conteudo
    )
    # Salva no banco
    db.add(nova_resposta)
    db.commit()
    db.refresh(nova_resposta)
    return nova_resposta

# Retorna todas as respostas salvas
@app.get("/historico", response_model=list[schemas.RespostaResponse])
def listar_historico(db: Session = Depends(get_db)):
    respostas = db.query(models.Resposta).all()
    return respostas

# Retorna uma resposta específica pelo ID
@app.get("/historico/{id}", response_model=schemas.RespostaResponse)
def buscar_resposta(id: int, db: Session = Depends(get_db)):
    resposta = db.query(models.Resposta).filter(models.Resposta.id == id).first()
    if resposta is None:
        raise HTTPException(status_code=404, detail="Resposta não encontrada")
    return resposta

# Remove uma resposta do histórico
@app.delete("/historico/{id}")
def deletar_resposta(id: int, db: Session = Depends(get_db)):
    resposta = db.query(models.Resposta).filter(models.Resposta.id == id).first()
    if resposta is None:
        raise HTTPException(status_code=404, detail="Resposta não encontrada")
    db.delete(resposta)
    db.commit()
    return {"mensagem": "Resposta deletada com sucesso"}