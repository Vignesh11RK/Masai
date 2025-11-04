# Spring Boot CI/CD Pipeline

This project demonstrates a complete CI/CD pipeline for a Spring Boot application using GitHub Actions and Docker Hub.

## 🚀 Features

- **Simple REST API**: A basic Spring Boot application with a `/hello` endpoint
- **Automated Testing**: JUnit tests that run on every push
- **Docker Containerization**: Multi-platform Docker images (AMD64 & ARM64)
- **CI/CD Pipeline**: Automated build, test, and deployment using GitHub Actions
- **Docker Hub Integration**: Automatic image publishing to Docker Hub

## 📋 Prerequisites

Before setting up the pipeline, ensure you have:

1. **GitHub Account**: To host your repository
2. **Docker Hub Account**: To store your container images
3. **Java 17+**: For local development
4. **Maven**: For building the application
5. **Docker**: For local container testing

## 🛠️ Setup Instructions

### 1. Docker Hub Configuration

1. Log in to [Docker Hub](https://hub.docker.com/)
2. Go to **Account Settings** → **Security**
3. Click **New Access Token**
4. Name it (e.g., "github-actions-token")
5. Copy the generated token (you won't see it again!)

### 2. GitHub Repository Setup

1. Create a new repository on GitHub
2. Push this project to your repository:
   ```bash
   git init
   git add .
   git commit -m "Initial commit: Spring Boot CI/CD setup"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
   git push -u origin main
   ```

### 3. GitHub Secrets Configuration

In your GitHub repository:

1. Go to **Settings** → **Secrets and variables** → **Actions**
2. Click **New repository secret** and add:
   - **DOCKERHUB_USERNAME**: Your Docker Hub username
   - **DOCKERHUB_TOKEN**: The access token from step 1

### 4. Repository Links

- **GitHub Repository**: `https://github.com/YOUR_USERNAME/YOUR_REPO`
- **Docker Hub Repository**: `https://hub.docker.com/r/YOUR_USERNAME/springboot-cicd`

## 🔄 Pipeline Workflow

The CI/CD pipeline automatically triggers on every push to the `main` branch and:

1. **Checks out** the latest code
2. **Sets up** JDK 17 environment
3. **Caches** Maven dependencies for faster builds
4. **Runs tests** - Pipeline stops if any test fails ❌
5. **Builds** the Spring Boot JAR file
6. **Creates** Docker image for multiple platforms
7. **Pushes** the image to Docker Hub with proper tags

## 🧪 Testing

Run tests locally:
```bash
mvn clean test
```

Build the application:
```bash
mvn clean package
```

## 🐳 Docker Usage

Build the Docker image locally:
```bash
docker build -t springboot-cicd .
```

Run the container:
```bash
docker run -p 8080:8080 springboot-cicd
```

Test the application:
```bash
curl http://localhost:8080/hello
# Expected output: Hello, CI/CD World!
```

## 📊 Pipeline Status

Monitor your pipeline:
- Go to your GitHub repository
- Click on the **Actions** tab
- View the workflow runs and their status

## 🏷️ Docker Image Tags

The pipeline creates multiple tags for better version management:
- `latest`: Latest build from main branch
- `main-<commit-sha>`: Specific commit from main branch
- `<branch-name>`: Branch-specific builds

## 🔧 Project Structure

```
├── .github/
│   └── workflows/
│       └── ci-cd.yml          # GitHub Actions workflow
├── src/
│   ├── main/
│   │   └── java/.../controller/
│   │       └── HelloController.java
│   └── test/
│       └── java/.../controller/
│           └── HelloControllerTest.java
├── Dockerfile                 # Container configuration
├── .dockerignore             # Docker build exclusions
├── pom.xml                   # Maven configuration
└── README.md                 # This file
```

## 🎯 Success Criteria

✅ **Pipeline completes successfully**  
✅ **Tests pass before Docker build**  
✅ **Docker image appears in Docker Hub**  
✅ **Application responds at /hello endpoint**  

## 🚨 Troubleshooting

**Tests failing?**
- Check test output in GitHub Actions logs
- Verify test assertions match expected values

**Docker build failing?**
- Ensure JAR file is built successfully
- Check Dockerfile syntax and paths

**Docker Hub push failing?**
- Verify DOCKERHUB_USERNAME and DOCKERHUB_TOKEN secrets
- Ensure Docker Hub repository exists or is public

**Application not responding?**
- Check if port 8080 is exposed correctly
- Verify Spring Boot application starts without errors
