# Variables
COMPOSE = docker compose
PROJECT_NAME = my-project

# Comandos
up:
	$(COMPOSE) up -d --build

down:
	$(COMPOSE) down
