//Script para las pestañas

document.addEventListener("DOMContentLoaded", () => {
      const tabs = document.querySelectorAll('.tab-button');
      const contents = document.querySelectorAll('.tab-content-custom');
      let activeTab = null;

      // Asegura que ninguna empiece abierta
      contents.forEach(c => c.classList.remove('active'));
      tabs.forEach(t => t.classList.remove('active'));

      tabs.forEach(tab => {
        tab.addEventListener('click', () => {
          const tabId = tab.getAttribute('data-tab');
          const content = document.getElementById(tabId);

          if (activeTab === tab) {
            tab.classList.remove('active');
            content.classList.remove('active');
            activeTab = null;
          } else {
            tabs.forEach(t => t.classList.remove('active'));
            contents.forEach(c => c.classList.remove('active'));

            tab.classList.add('active');
            content.classList.add('active');
            activeTab = tab;
          }
        });
      });
    });