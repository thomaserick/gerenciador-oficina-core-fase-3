#!/bin/bash
set -e

# =============================
# Deploy da aplicação Spring Boot + PostgreSQL no Kubernetes local com Docker
# =============================

NAMESPACE="gerenciador-oficina-core"

echo "🚀 Iniciando deploy Kubernetes no namespace: $NAMESPACE"

# Verifica se o namespace existe
if ! kubectl get namespace "$NAMESPACE" &>/dev/null; then
  echo "📦 Criando namespace $NAMESPACE..."
  kubectl apply -f ./devops/k8s/dev/namespace.yaml
else
  echo "✅ Namespace $NAMESPACE já existe."
fi

echo "🔐 Aplicando secrets..."
kubectl apply -f ./devops/k8s/dev/postgres-secret.yaml -n $NAMESPACE

echo "🐘 Subindo PostgreSQL..."
kubectl apply -f ./devops/k8s/dev/postgres-deployment.yaml -n $NAMESPACE

echo "☕ Subindo aplicação Spring Boot..."
kubectl apply -f ./devops/k8s/dev/deployment.yaml -n $NAMESPACE

echo "🌐 Criando service para expor aplicação..."
kubectl apply -f ./devops/k8s/dev/services.yaml -n $NAMESPACE

echo "📈 Aplicando Horizontal Pod Autoscaler..."
kubectl apply -f ./devops/k8s/dev/hpa.yaml -n $NAMESPACE

echo "✅ Deploy concluído!"
echo "-------------------------------------------"
echo "Verifique os pods com:"
echo "  kubectl get pods -n $NAMESPACE"
echo ""
echo "Acesse a aplicação em: http://localhost:8081/swagger-ui/index.html"
echo "-------------------------------------------"
