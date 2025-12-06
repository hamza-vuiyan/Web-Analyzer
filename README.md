# Web-Analyzer

A powerful and comprehensive web analysis tool that helps you analyze, monitor, and optimize your websites. Get detailed insights into performance, SEO, accessibility, and security.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)

## 🚀 Features

- **Performance Analysis**: Measure page load times, resource sizes, and render performance
- **SEO Auditing**: Analyze meta tags, headings, alt texts, and search engine optimization
- **Accessibility Checks**: Ensure your website meets WCAG standards
- **Security Scanning**: Detect common vulnerabilities and security issues
- **Mobile Responsiveness**: Test how your site performs on different devices
- **Broken Link Detection**: Find and report broken links across your website
- **Analytics Integration**: Track user behavior and site metrics
- **Detailed Reporting**: Generate comprehensive reports in multiple formats (PDF, HTML, JSON)

## 📋 Prerequisites

Before you begin, ensure you have met the following requirements:

- Node.js (v14.0 or higher)
- npm or yarn package manager
- Modern web browser (Chrome, Firefox, Safari, or Edge)

## 🔧 Installation

### Using npm

```bash
npm install web-analyzer
```

### Using yarn

```bash
yarn add web-analyzer
```

### From source

```bash
# Clone the repository
git clone https://github.com/hamza-vuiyan/Web-Analyzer.git

# Navigate to the directory
cd Web-Analyzer

# Install dependencies
npm install

# Build the project
npm run build
```

## 💻 Usage

### Basic Usage

```javascript
const WebAnalyzer = require('web-analyzer');

// Initialize the analyzer
const analyzer = new WebAnalyzer({
  url: 'https://example.com',
  options: {
    performance: true,
    seo: true,
    accessibility: true
  }
});

// Run the analysis
analyzer.analyze().then(results => {
  console.log(results);
});
```

### CLI Usage

```bash
# Analyze a single URL
web-analyzer analyze https://example.com

# Generate a report
web-analyzer analyze https://example.com --report html

# Analyze multiple pages
web-analyzer analyze https://example.com --crawl --depth 3

# Check specific aspects only
web-analyzer analyze https://example.com --checks seo,performance
```

### Advanced Configuration

```javascript
const analyzer = new WebAnalyzer({
  url: 'https://example.com',
  options: {
    // Performance settings
    performance: {
      enabled: true,
      metrics: ['FCP', 'LCP', 'TTI', 'CLS'],
      threshold: {
        loadTime: 3000,
        firstPaint: 1000
      }
    },
    
    // SEO settings
    seo: {
      enabled: true,
      checkMetaTags: true,
      checkHeadings: true,
      checkImages: true
    },
    
    // Accessibility settings
    accessibility: {
      enabled: true,
      standard: 'WCAG2AA',
      includeWarnings: true
    },
    
    // Security settings
    security: {
      enabled: true,
      checkSSL: true,
      checkHeaders: true,
      checkMixedContent: true
    }
  }
});
```

## 📊 Example Output

```json
{
  "url": "https://example.com",
  "timestamp": "2024-01-15T10:30:00.000Z",
  "performance": {
    "score": 85,
    "metrics": {
      "loadTime": 2.4,
      "firstContentfulPaint": 1.2,
      "largestContentfulPaint": 2.1,
      "timeToInteractive": 3.5
    }
  },
  "seo": {
    "score": 92,
    "issues": [],
    "recommendations": [
      "Add meta description to improve search appearance"
    ]
  },
  "accessibility": {
    "score": 78,
    "violations": [
      {
        "type": "missing-alt",
        "count": 3,
        "severity": "moderate"
      }
    ]
  }
}
```

## 🔍 API Reference

### `WebAnalyzer(config)`

Creates a new instance of the Web Analyzer.

**Parameters:**
- `config.url` (string) - The URL to analyze
- `config.options` (object) - Configuration options

**Methods:**

#### `analyze()`
Performs the web analysis and returns results.

Returns: `Promise<AnalysisResults>`

#### `generateReport(format)`
Generates a report in the specified format.

Parameters:
- `format` (string) - Report format ('html', 'pdf', 'json', 'csv')

Returns: `Promise<string>`

#### `crawl(options)`
Crawls the website and analyzes multiple pages.

Parameters:
- `options.depth` (number) - Maximum crawl depth
- `options.maxPages` (number) - Maximum pages to analyze

Returns: `Promise<CrawlResults>`

## ⚙️ Configuration Options

| Option | Type | Default | Description |
|--------|------|---------|-------------|
| `url` | string | required | The URL to analyze |
| `timeout` | number | 30000 | Request timeout in milliseconds |
| `userAgent` | string | auto | Custom user agent string |
| `performance` | boolean/object | true | Enable performance analysis |
| `seo` | boolean/object | true | Enable SEO auditing |
| `accessibility` | boolean/object | true | Enable accessibility checks |
| `security` | boolean/object | true | Enable security scanning |

## 🧪 Testing

Run the test suite:

```bash
# Run all tests
npm test

# Run tests with coverage
npm run test:coverage

# Run specific test suite
npm test -- --grep "Performance"
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some amazing feature'`)
5. Push to the branch (`git push origin feature/amazing-feature`)
6. Open a Pull Request

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Md. Amir Hamza** - *Initial work* - [hamza-vuiyan](https://github.com/hamza-vuiyan)

## 🙏 Acknowledgments

- Thanks to all contributors who have helped with this project
- Inspired by tools like Lighthouse, WebPageTest, and Axe
- Built with modern web technologies and best practices

## 📧 Contact

- GitHub: [@hamza-vuiyan](https://github.com/hamza-vuiyan)
- Project Issues: [GitHub Issues](https://github.com/hamza-vuiyan/Web-Analyzer/issues)

## 🗺️ Roadmap

- [ ] Add support for batch URL analysis
- [ ] Implement real-time monitoring
- [ ] Create web dashboard interface
- [ ] Add custom plugin system
- [ ] Support for CI/CD integration
- [ ] Historical data comparison
- [ ] Automated scheduling and alerts

## 📚 Resources

- [Documentation](https://github.com/hamza-vuiyan/Web-Analyzer/wiki)
- [API Reference](https://github.com/hamza-vuiyan/Web-Analyzer/wiki/API)
- [Changelog](CHANGELOG.md)
- [FAQ](https://github.com/hamza-vuiyan/Web-Analyzer/wiki/FAQ)

---

Made with ❤️ by [Md. Amir Hamza](https://github.com/hamza-vuiyan)