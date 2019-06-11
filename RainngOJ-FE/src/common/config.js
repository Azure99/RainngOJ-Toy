let config = {}

config.backEndUrl = 'http://localhost:8085/api/v1'

config.languages = [{ name: 'C', id: 0 }, { name: 'C++', id: 1 }, { name: 'Java', id: 2 }, { name: 'Python', id: 3 }]
config.dbLanguages = [{ name: 'MySQL', id: 20 }]

config.getLanguageNameById = function (id) {
  for (let i = 0; i < config.languages.length; i++) {
    if (config.languages[i].id === id) {
      return config.languages[i].name
    }
  }

  for (let i = 0; i < config.dbLanguages.length; i++) {
    if (config.dbLanguages[i].id === id) {
      return config.dbLanguages[i].name
    }
  }

  return '未知'
}

export default config
