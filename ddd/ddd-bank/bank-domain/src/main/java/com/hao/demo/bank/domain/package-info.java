/**
 * Domain 层，核心业务逻辑的集中地，纯 POJO，理论上仅依赖 Types
 * 但为了最终能使用基础设施（包括 DB、中间件、外部服务等），还需要在当前的 module 声明各个 infrastructure 的接口，但不提供实现
 * 因此只有 domain 包下属于 Domain 层，external、messaging 和 repository 虽然出现在当前的 domain module 但属于 infrastructure 层
 * 这样组织的目的是为了通过依赖倒置（仅依赖接口不依赖实现），让 domain 层解耦掉对各个 infrastructure 的强依赖，做到在理论上仅依赖 Types
 * 同时最终在 starter 中组和起来时可以真正调用到 infrastructure 的各个底层服务（利用 spring 的 DI 机制）
 */
package com.hao.demo.bank.domain;