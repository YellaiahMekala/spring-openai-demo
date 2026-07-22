# Enterprise Spring AI Support Assistant & Token Gateway

A production-grade Spring Boot microservice demonstrating advanced LLM orchestration using **Spring AI**. This project implements an intelligent Customer Order Support Assistant protected by a robust **AOP-based Advisor Gateway** to handle cross-cutting concerns like pre-execution token estimation, PII redaction, and denial-of-wallet protection.

##  Overview
In enterprise LLM integrations, exposing generative AI endpoints without strict token governance and security guardrails leads to API cost blowouts and compliance violations. This service abstracts the underlying LLM (e.g., OpenAI, AWS Bedrock, GCP Vertex AI) and routes all requests through the **Spring AI CallAdvisor API**, enforcing zero-latency security policies *before* network execution.

##  System Architecture & Advisor Chain

The application utilizes a **Last-In, First-Out (LIFO) Interceptor Stack** to process prompts.

## Tech Stack & Core Ecosystem Libraries
Framework: Java 21, Spring Boot 3.x, Spring AI

##  System Architecture & Advisor Chain Running the Application
Prerequisites
Java 21+
Maven 3.8+

## Core Components
PreAndPostTokenAuditAdvisor: A highly optimized interceptor that uses offline token counting
(JTokkitTokenCountEstimator) to estimate payload size. It short-circuits malicious or oversized requests,
returning a synthetic fallback response without consuming cloud API billing. Post-execution, it captures exact token metadata for telemetry.

OrderSupportAISupportAssistantService: The core business service utilizing ChatClient.
It demonstrates stateless singleton bean injection for advisors and 
externalized prompt management using StringTemplate (.st) files for strict prompt engineering version control.

## External Libraries and Tools used

Tokenization: jtokkit (for exact offline Byte Pair Encoding token counting)

Observability: Micrometer & SLF4J (for telemetry and token usage tracking)

Containerization: Docker & Kubernetes (designed for stateless, horizontal pod auto-scaling in AWS

```text


A valid LLM API Key (e.g., OPENAI_API_KEY) set in your environment variables
[Incoming User Prompt: Customer Support Request]
             │
             ▼
┌─────────────────────────────────────────────────────────────┐
│ 1. Token Audit Advisor (Order: 1)                           │
│    ├── JTokkit Local BPE Token Estimation                   │
│    └── Short-Circuits if Prompt > Max Allowance             │
└──────────────────────┬──────────────────────────────────────┘
                       │ (If within limits)
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ 2. SafeGuard Advisor (Order: 2)                             │
│    └── Scans for PII (OTP, CVV, Passwords)                  │
└──────────────────────┬──────────────────────────────────────┘
                       │ (If safe)
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ 3. Spring AI ChatClient (LLM Execution Boundary)            │
│    └── Executes Network Call to Cloud/Local Inference Engine│
└──────────────────────┬──────────────────────────────────────┘
                       │
             [Returns Synthesized Response]

