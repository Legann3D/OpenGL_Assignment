#version 330

in vec4 fragColour;  
in vec4 transformedPosition;

out vec4 finalColor;

void main() {
    finalColor = fragColour;
}
