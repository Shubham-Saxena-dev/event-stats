.DEFAULT_GOAL := help

# Make version
ifneq (,)
$(error This makefile requires GNU Make)
endif

ARGS = $(filter-out $@,$(MAKECMDGOALS))
MAKEFLAGS += --silent

.PHONY: clean-package
clean-package:
	mvn clean package

.PHONY: up
up: clean-package
	docker-compose up -d --remove-orphans

.PHONY: build_up
build_up: clean-package
	docker-compose up -d --build --remove-orphans

.PHONY: start
start: clean-package ## Start the containers
	docker-compose start

.PHONY: stop
stop: ## Stop the containers
	docker-compose stop

.PHONY: swagger-ui
swagger-ui:
	open GET http://localhost:8080/swagger-ui/index.html
