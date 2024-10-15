USE disaster_response;
CREATE TABLE IF NOT EXISTS disaster_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    disaster_name VARCHAR(100) NOT NULL,
    disaster_category VARCHAR(50) NOT NULL
);

-- Populate the table with disaster types
INSERT INTO disaster_types (disaster_name, disaster_category) VALUES 
( 'Earthquake', 'Natural'),
( 'Tsunami', 'Natural'),
( 'Volcanic Eruption', 'Natural'),
( 'Flood', 'Natural'),
( 'Hurricane/Cyclone/Typhoon', 'Natural'),
( 'Tornado', 'Natural'),
( 'Drought', 'Natural'),
( 'Landslide/Mudslide', 'Natural'),
( 'Wildfire', 'Natural'),
( 'Extreme Heat Wave', 'Natural'),
( 'Avalanche', 'Natural'),
( 'Storm Surge', 'Natural'),
( 'Extreme Cold Wave', 'Natural'),
( 'Industrial Accident', 'Man-Made'),
( 'Nuclear Accident', 'Man-Made'),
( 'Oil Spill', 'Man-Made'),
( 'Building Collapse', 'Man-Made'),
( 'Transport Accident', 'Man-Made'),
( 'Terrorist Attack', 'Man-Made'),
( 'Arson', 'Man-Made'),
( 'Resource Depletion', 'Man-Made'),
( 'Environmental Pollution', 'Man-Made'),
( 'Cyber Attack', 'Man-Made'),
( 'Climate Change', 'Hybrid'),
( 'Pandemic', 'Hybrid');
