Creating an expanded `README.md` file for the React Tower Defense game, I'll elaborate on the gameplay, including map and tower selection, strategies for defeating multiple waves of zombies, and the technical aspects such as the use of trigonometry, geometric functions, and HTML canvas. I'll also highlight the achievement of being a top 3 project and the storage of highscore information in the Docker container.

---

# React Tower Defense Game

## Introduction
React Tower Defense, a top 3 project in COM S/SE 319, is a sophisticated tower defense game developed using React. This game stands out for its combination of innovative gameplay mechanics, intuitive user interfaces, and advanced software design principles. It challenges players to defend their territory against waves of zombies using a variety of towers, each with unique capabilities and upgrades. The game's success is attributed to its engaging gameplay, seamless user experience, effective use of modern web technologies, and sophisticated mathematical modeling. It utilizes SOAP APIs for backend communication, Docker for consistent deployment, and HTML canvas for dynamic rendering, making it a benchmark in educational software construction and user interface design.

## Features
React Tower Defense offers an immersive gaming experience with a rich set of features:
- **Dynamic Gameplay:** Players face increasingly challenging waves of zombies, requiring strategic tower placement and upgrades.
- **Variety of Towers:** The game includes multiple tower types, each with unique abilities and upgrade paths.
- **Interactive Maps:** Multiple maps with varying layouts challenge players to adapt their strategies.
- **Real-Time Strategy Elements:** Quick decision-making is key in this real-time gameplay.
- **Responsive UI:** Built with React, the UI is sleek and intuitive.
- **In-Game Shop:** Players can purchase upgrades and special items.
- **Health and Score System:** Tracks player performance, with health bars for towers and zombies.
- **Highscore Storage:** Highscore information is stored within the Docker container, ensuring data persistence and security.
- **Advanced Mathematical Modeling:** Uses trigonometry and geometric functions to manage the spatial complexities of each object.
- **HTML Canvas:** Leverages HTML canvas for dynamic and responsive rendering of game elements.

## Installation
Ensure Node.js, npm, and Docker are installed. Follow these steps:
1. Clone the repository: `git clone [repository URL]`.
2. Navigate to the directory: `cd react-tower-defense`.
3. Install dependencies: `npm install`.
4. Use Docker to build and run the application: `docker-compose up`.
5. Access the game at `localhost:3000`.

## Gameplay Tutorial
### Starting the Game
Navigate to `localhost:3000` in your browser to begin. You'll be greeted with a main menu where you can start the game, view highscores, or adjust settings.

### Map Selection
Choose from a variety of maps, each offering unique challenges and layouts. The map you select will significantly influence your strategy, as each has different path configurations and strategic points.

### Tower Selection and Placement
- **Choosing Towers:** You have a range of towers to choose from, each with specific strengths and weaknesses. Some towers may be better suited for certain maps or zombie types.
- **Placing Towers:** Drag and drop towers onto the map. Consider the range, attack speed, and special abilities of each tower to maximize their effectiveness.
- **Upgrading Towers:** Use in-game currency earned from defeating zombies to upgrade your towers, enhancing their range, damage, or adding special abilities.

### Defeating Waves of Zombies
- **Wave Progression:** Zombies will come in waves, each wave increasing in difficulty. Be prepared to adapt your strategy as you progress.
- **Strategic Planning:** Use the pause feature to strategically place and upgrade towers during intense moments.
- **Utilizing Geometry:** The game's use of trigonometry and geometric functions means that the positioning and angle of your towers can significantly impact their effectiveness.

### Highscores and Progress
Your highscore is automatically saved in the Docker container, allowing you to compete against yourself or others who have played the game on the same server.

## Design and Architecture
The game's architecture, built on React, is modular and maintainable. It separates game logic from UI components, following best practices in software design. The integration with SOAP APIs and Docker highlights the project's commitment to industry standards. The use of HTML canvas for rendering and advanced mathematical models for managing spatial complexities showcases the technical depth of the project.

## Testing
The game underwent rigorous testing, including unit, integration, and performance testing, to ensure a bug-free experience. Automated and manual testing methodologies were employed to cover various scenarios.

## Contributing
Contributions are welcome. Fork the repository, make changes, and submit a pull request. We value bug reports, feature suggestions, and code improvements.

## Team
- Sami Bensellam
- Abdelrahman Mohammed
- Maxim Hjelmstad
- Jake Garnett

## Acknowledgments
Special thanks to the instructors and peers in COM S/SE 319 for their support and feedback, instrumental in shaping this project into a top-tier educational tool and engaging gaming experience.

## License
React Tower Defense is released under the MIT License. See the [LICENSE](LICENSE.md) file for details.

---

This README provides a comprehensive overview of the React Tower Defense game, covering gameplay, technical aspects, and the project's achievements. If you have any more modifications or specific details to add, please let me know!
