# Project Summary  

From the beginning of this assignment, I kept repeating one phrase:  
**“Keep it simple, don’t over-engineer it.”**  

I focused on the basics: **MVVM with Compose UI**, but made it scalable.  

### How?  
- Adopted **Clean Architecture** from the start. While it might seem excessive for a small project, it gave us strong testability and future scalability.  
- Used **Koin instead of Hilt** and **Ktor instead of Retrofit**, providing flexibility to move to **Kotlin Multiplatform (KMP)** at any time.  
- Maintained **separated layers**, enabling the extraction of shared libraries when needed.  
- Applied **Compose theming**, making it easy to adopt any design system—or even load one dynamically from the backend.  
- Integrated **Rive animations**, keeping the app engaging with smooth motion and only a **375 KB file size**.  
- Organized all **shared widgets** into dedicated files for maximum reusability.  

The project is not perfect—no project ever is—but it provides a strong base.  
There’s always room for enhancement, optimization, and creativity.  
I believe in the phrase: **“Build it first, then optimize it.”**

---

## Difficulties  

I faced two main challenges:  

- **API inconsistencies**: The search API reused the same model as `MainContent` but with different data types. This required creating a separate model even though the parameters looked identical.  

---

## Enhancements (Next Steps)  

- Adopt a **multi-module architecture** by feature.  
- Extract **Remote & Local libraries** for reuse across Android and KMP projects.  
- Implement **CI/CD pipelines** using Fastlane.  
- Integrate the **Mobile.dev framework** for simpler UI testing.  
- Add **Room** for local caching and offline content support.  

---

## Preview  
<img src="/screenshot.png" alt="Project Preview" width="400"/>
