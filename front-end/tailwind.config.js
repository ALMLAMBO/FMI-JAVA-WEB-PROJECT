const { createGlobPatternsForDependencies } = require('@nx/angular/tailwind');
const { join } = require('path');

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    join(__dirname, 'src/**/!(*.stories|*.spec).{ts,html}'),
    ...createGlobPatternsForDependencies(__dirname),
  ],
  theme: {
    extend: {
      colors: {
        'base': 'var(--base)',
        'base-muted': 'var(--base-muted)',
        'contrast': 'var(--contrast)',
        'contrast-muted': 'var(--contrast-muted)',
        'accent': 'var(--accent)',
        'accent-contrast': 'var(--accent-contrast)',
        'primary': 'var(--primary)',
        'primary-contrast': 'var(--primary-contrast)',
        'warn': 'var(--warn)',
        'warn-contrast': 'var(--warn-contrast)'
      }
    },
  },
  plugins: [],
};