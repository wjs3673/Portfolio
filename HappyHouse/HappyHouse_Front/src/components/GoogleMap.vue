<template>
  <div>
    <!-- <div>
      <h2>Search and add a pin</h2>
      <label>
        <gmap-autocomplete @place_changed="setPlace"> </gmap-autocomplete>
        <button @click="addMarker">Add</button>
      </label>
      <br />
    </div>
    <br /> -->
    <gmap-map
      :center="center"
      :zoom="zoomNum"
      style="width:100%;  height: 700px;"
    >
      <gmap-marker
        :key="`${index}_apts`"
        v-for="(m, index) in aptMarkers"
        :position="m.position"
        :icon="{
          url: 'http://maps.google.com/mapfiles/ms/micons/red-pushpin.png',
        }"
        @click="getMarkerDetail(m)"
      >
        <gmap-info-window
          :options="{ maxWidth: 300 }"
          :opened="m.infoWindowOpen"
        >
          <span class="infoWindow">[아파트]</span><br />
          <span class="infoWindow">{{ m.label }}</span>
        </gmap-info-window>
      </gmap-marker>

      <gmap-marker
        :key="`${index}_residences`"
        v-for="(m, index) in residenceMarkers"
        :position="m.position"
        :icon="{
          url: 'http://maps.google.com/mapfiles/ms/micons/blue-pushpin.png',
        }"
        @click="getMarkerDetail(m)"
      >
        <gmap-info-window
          :options="{ maxWidth: 300 }"
          :opened="m.infoWindowOpen"
        >
          <span class="infoWindow">[연립다세대]</span><br />
          <span class="infoWindow">{{ m.label }}</span>
        </gmap-info-window>
      </gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'GoogleMap',
  data() {
    return {
      // default to Montreal to keep it simple
      // change this to whatever makes sense
      center: { lat: 37.5642135, lng: 127.0016985 }, //서울시
      aptMarkers: [],
      residenceMarkers: [],
      zoomNum: 12,
      prevM: null,
    };
  },

  props: {
    aptdeals: { type: Array },
    apts: { type: Array },
    value: { type: Array },
    residences: { type: Array },
  },

  watch: {
    value: function() {
      this.getAptMarkers();
    },

    aptdeals: function() {
      this.getAptMarkers();
    },

    apts: function() {
      this.getAptMarkers();
    },

    residences: function() {
      this.getResidenceMarkers();
    },
  },

  methods: {
    getMarkerDetail(m) {
      //마커 클릭 시
      if (this.prevM != null) {
        this.prevM.infoWindowOpen = false;
      }
      this.center = m.position;
      m.infoWindowOpen = true;
      this.zoomNum = 16;
      this.prevM = m;

      this.$emit('mapDetail', m, m.type);
    },

    getAptMarkers() {
      //동 선택 후 마커 표시
      this.aptMarkers = [];
      this.prevM = null;
      for (let i = 0; i < this.apts.length; ++i) {
        let j;
        if (this.aptdeals.length != 0) {
          for (j = 0; j < this.aptdeals.length; ++j) {
            if (this.aptdeals[j].aptName === this.apts[i].aptName) {
              break;
            }
          }
        }

        //console.log(parseInt(this.aptdeals[j].dealAmount.replace(/,/g, '')));
        // console.log(this.value);
        let amount = parseInt(this.aptdeals[j].dealAmount.replace(/,/g, ''));

        if (amount >= this.value[0] * 1000 && amount <= this.value[1] * 1000) {
          let addr =
            this.apts[i].dong +
            '+' +
            this.apts[i].aptName +
            '+' +
            this.apts[i].jibun;
          axios
            .get('https://maps.googleapis.com/maps/api/geocode/json', {
              params: {
                key: 'AIzaSyA_sZ7b3Byd5yoJLdXF5KARqHzZI3kCKQ4',
                address: addr,
              },
            })
            .then((response) => {
              if (response.data.results.length > 0) {
                const marker = response.data.results[0].geometry.location;
                this.aptMarkers.push({
                  position: marker,
                  label: this.apts[i].aptName,
                  type: 'apt',
                  infoWindowOpen: false,
                });
                this.center = this.aptMarkers[0].position;
              }
            })
            .catch((error) => {
              console.log(error);
            });
        }
      }
      this.zoomNum = 14;
    },

    getResidenceMarkers() {
      //동 선택 후 마커 표시
      this.residenceMarkers = [];
      this.prevM = null;
      for (let i = 0; i < this.residences.length; ++i) {
        let addr =
          this.residences[i].dong +
          '+' +
          this.residences[i].buildingName +
          '+' +
          this.residences[i].bunji;
        axios
          .get('https://maps.googleapis.com/maps/api/geocode/json', {
            params: {
              key: 'AIzaSyA_sZ7b3Byd5yoJLdXF5KARqHzZI3kCKQ4',
              address: addr,
            },
          })
          .then((response) => {
            if (response.data.results.length > 0) {
              const marker = response.data.results[0].geometry.location;
              this.residenceMarkers.push({
                position: marker,
                label: this.residences[i].buildingName,
                type: 'residence',
                infoWindowOpen: false,
              });

              this.center = this.residenceMarkers[0].position;
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
      this.zoomNum = 14;
    },
  },
};
</script>
<style>
.infoWindow {
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 700;
}
</style>
