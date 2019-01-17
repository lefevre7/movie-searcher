# movie-searcher
Searches movies from TMDB

## Usage

1. Clone https://github.com/lefevre7/movie-searcher

2. Open webapp and webserver (with gradle.settings) as different projects.

3. Make sure to have Kotlin (use 1.3.11 or latest because earlier versions gave me problems) and Gradle (4.x latest not 5.x because that caused me problems) installed.

4. Make sure Kotlin is configured (for Intellij it's in the project structure>problems>configure Kotlin and/or structure>facets>configure Kotlin).

5. Run the backend (webserver) which uses port 8081.

6. Run the frontend (webapp) which uses port 8082 (npm install, npm run dev) (with node v10.15.0 and npm 6.4.1 because v11.x gave me problems).

7. Go to http://localhost:8082 and search a movie title to view the returned movies' poster, name, popularity, and overview.
